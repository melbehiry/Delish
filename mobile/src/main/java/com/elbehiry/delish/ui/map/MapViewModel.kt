/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elbehiry.delish.ui.map

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elbehiry.delish.ui.util.WhileViewSubscribed
import com.elbehiry.model.LocationModel
import com.elbehiry.model.VenuesItem
import com.elbehiry.shared.domain.location.GetCurrentLocationUseCase
import com.elbehiry.shared.domain.restaurants.CreateFoursquareVersionUseCase
import com.elbehiry.shared.domain.restaurants.SearchRestaurantsUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.shareIn
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

const val cameraUpdatesKey = "cameraUpdatesKey"

@HiltViewModel
class MapViewModel @Inject constructor(
    private val searchRestaurantsUseCase: SearchRestaurantsUseCase,
    private val createFoursquareVersionUseCase: CreateFoursquareVersionUseCase,
    private val getCurrentLocationUseCase: GetCurrentLocationUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _errorMessage = Channel<String>(1, BufferOverflow.DROP_LATEST)
    val errorMessage: Flow<String> =
        _errorMessage.receiveAsFlow().shareIn(viewModelScope, WhileViewSubscribed)

    private val _mapCenterEvent = Channel<CameraUpdate>(Channel.CONFLATED)
    val mapCenterEvent = _mapCenterEvent.receiveAsFlow()

    private val _mapVariant = MutableStateFlow<MapVariant?>(null)
    val mapVariant: StateFlow<MapVariant?> = _mapVariant

    private val _selectedMarkerInfo = MutableStateFlow<VenuesItem?>(null)
    val selectedMarkerInfo: StateFlow<VenuesItem?> = _selectedMarkerInfo

    private val _venuesList = MutableStateFlow<List<VenuesItem>>(emptyList())
    val venuesList: StateFlow<List<VenuesItem>> = _venuesList

    private val getRestaurants = MutableSharedFlow<LatLng?>()
    private val getRestaurantsUsingLocation = MutableSharedFlow<LatLng>()

    private val viewState: StateFlow<Result<List<VenuesItem>?>> =
        getRestaurants
            .flatMapLatest { location ->
                if (location == null || location.latitude == 0.0) {
                    getCurrentLocationUseCase(Unit)
                } else {
                    flowOf(Result.Success(LocationModel(location.latitude, location.longitude)))
                }
            }.onEach {
                if (it is Result.Error) {
                    _errorMessage.trySend(it.exception.message ?: "Error")
                }
            }.flatMapLatest { locationModel ->
                val params = SearchRestaurantsUseCase.Params.create(
                    latLng = "${locationModel.data?.lat ?: 0.0},${locationModel.data?.long ?: 0.0}",
                    version = createFoursquareVersionUseCase(Date())
                )
                searchRestaurantsUseCase(params)
            }.onEach {
                if (it is Result.Error) {
                    _errorMessage.trySend(it.exception.message ?: "Error")
                }
            }.stateIn(viewModelScope, WhileViewSubscribed, Result.Loading)

    val isLoading: StateFlow<Boolean> = viewState
        .mapLatest {
            it == Result.Loading
        }.stateIn(viewModelScope, WhileViewSubscribed, false)

    init {
        viewModelScope.launch {
            viewState
                .filter { it is Result.Success && !it.data.isNullOrEmpty() }
                .mapLatest {
                    it.data
                }.collect { venues ->
                    venues?.let {
                        _venuesList.value = venues
                    }
                }
        }
        viewModelScope.launch {
            getRestaurantsUsingLocation
                .debounce(300)
                .distinctUntilChanged { old, new ->
                    old.latitude == new.latitude &&
                        old.longitude == new.longitude
                }
                .collect {
                    getRestaurants.emit(it)
                }
        }
    }

    /**
     * Validate if there is camera updates in the cache.
     * Call the get restaurants near using current location.
     */
    fun getRestaurantByCurrentLocation() {
        val cameraUpdates: LatLng? = savedStateHandle[cameraUpdatesKey]
        cameraUpdates?.let {
            offerCameraUpdates(cameraUpdates)
        }
        viewModelScope.launch {
            getRestaurants.emit(null)
        }
    }

    /**
     * This to get the restaurant using a specific position, depending on camera position.
     *
     * @param target
     */
    fun getRestaurantsInPosition(target: LatLng?) {
        target?.let {
            viewModelScope.launch {
                getRestaurantsUsingLocation.emit(target)
            }
        }
    }

    /**
     * Highlight a specific venue item and move the camera towards.
     *
     * @param venuesItem
     * @param immediateHighLight
     * @param shouldZoomCamera
     */
    fun highlightVenuesItem(
        venuesItem: VenuesItem?,
        immediateHighLight: Boolean = false,
        shouldZoomCamera: Boolean = false
    ) {
        if (immediateHighLight || _selectedMarkerInfo.value == null) {
            venuesItem?.let {
                _selectedMarkerInfo.value = venuesItem
                if (shouldZoomCamera) {
                    val latLng = LatLng(venuesItem.location.lat, venuesItem.location.lng)
                    saveCurrentLatLng(latLng)
                    offerCameraUpdates(latLng)
                }
            }
        }
    }

    private fun saveCurrentLatLng(latLng: LatLng) {
        savedStateHandle[cameraUpdatesKey] = latLng
    }

    private fun offerCameraUpdates(latLng: LatLng) {
        val update = CameraUpdateFactory.newLatLngZoom(latLng, 16.4f)
        _mapCenterEvent.trySend(update)
    }

    fun setMapVariant(variant: MapVariant) {
        _mapVariant.value = variant
    }
}
