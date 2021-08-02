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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Surface
import androidx.compose.material.primarySurface
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.rounded.Layers
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.CustomAccessibilityAction
import androidx.compose.ui.semantics.customActions
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.compose.ui.window.Popup
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.widget.LoadingContent
import com.google.accompanist.insets.navigationBarsPadding
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MapStyleOptions
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.ktx.awaitMap
import kotlinx.coroutines.flow.collect

@Composable
fun MapViewContent(
    viewModel: MapViewModel,
    onBackClicked: () -> Unit,
) {
    val mapView = rememberMapViewWithLifecycle()
    val venuesList by viewModel.venuesList.collectAsState()
    val loading by viewModel.isLoading.collectAsState()
    val mapVariants by viewModel.mapVariant.collectAsState()

    var variantsPopupShown by remember { mutableStateOf(false) }
    if (variantsPopupShown) {
        MapVariantsPopup(
            MapVariant.values().toMutableList(),
            {
                variantsPopupShown = false
            }
        ) {
            variantsPopupShown = false
            viewModel.setMapVariant(it)
        }
    }

    mapVariants?.let { item ->
        mapView.getMapAsync { map ->
            map.setMapStyle(
                MapStyleOptions.loadRawResourceStyle(
                    mapView.context, item.styleResId
                )
            )
        }
    }

    Scaffold(
        backgroundColor = MaterialTheme.colors.primarySurface,
        topBar = { MapTopBar { onBackClicked() } },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    variantsPopupShown = !variantsPopupShown
                },
                modifier = Modifier
                    .navigationBarsPadding()
            ) {
                Icon(
                    imageVector = Icons.Rounded.Layers,
                    contentDescription = stringResource(R.string.map)
                )
            }
        }
    ) {

        LoadingContent(loading) {
            LaunchedEffect(mapView) {
                mapView.awaitMap().apply {
                    venuesList.forEach { venue ->
                        val latLng = LatLng(venue.location.lat, venue.location.lng)
                        val marker = this.addMarker(
                            MarkerOptions().position(latLng).title(venue.name)
                        )
                        marker.tag = venue
                        viewModel.highlightVenuesItem(
                            venuesItem = venue,
                            shouldZoomCamera = true
                        )
                    }
                }

                viewModel.mapCenterEvent.collect { update ->
                    mapView.getMapAsync {
                        it.animateCamera(update)
                    }
                }
            }

            AndroidView({ mapView })
        }
    }
}

@Composable
fun MapTopBar(onBackClicked: () -> Unit) {
    TopAppBar(
        elevation = 6.dp,
        title = {
            Row {
                IconButton(
                    onClick = {
                        onBackClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.map)
                    )
                }
                Text(
                    text = stringResource(R.string.map),
                    modifier = Modifier
                        .padding(start = 4.dp)
                        .align(Alignment.CenterVertically)
                )
            }
        }
    )
}

@Composable
fun MapVariantsPopup(
    variants: MutableList<MapVariant>,
    onDismiss: () -> Unit,
    onVariant: (MapVariant) -> Unit
) {
    val openDialog = remember { mutableStateOf(true) }

    if (openDialog.value) {
        Box {
            val cornerSize = 16.dp
            Popup(
                alignment = Alignment.BottomEnd,
                onDismissRequest = onDismiss
            ) {
                Box(
                    Modifier
                        .width(200.dp)
                        .padding(bottom = 75.dp)
                        .background(Color.White, RoundedCornerShape(cornerSize))
                ) {
                    LazyColumn {
                        items(variants) { item ->
                            VariantItem(item) {
                                openDialog.value = false
                                onVariant(it)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun VariantItem(
    mapVariant: MapVariant,
    modifier: Modifier = Modifier,
    onVariant: (MapVariant) -> Unit
) {

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 16.dp)
            .clickable { onVariant(mapVariant) },
        color = Color.DarkGray,
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier
                .semantics {
                    customActions = listOf(
                        CustomAccessibilityAction(
                            label = "",
                            action = { true }
                        )
                    )
                }
        ) {
            Icon(
                painter = painterResource(id = mapVariant.iconResId),
                tint = Color.White,
                contentDescription = stringResource(id = mapVariant.labelResId),
                modifier = Modifier
                    .padding(
                        vertical = 4.dp,
                        horizontal = 4.dp
                    )
            )

            Text(
                text = stringResource(id = mapVariant.labelResId),
                style = MaterialTheme.typography.body1,
                textAlign = TextAlign.Start,
                maxLines = 1,
                color = Color.White,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(
                        vertical = 8.dp,
                        horizontal = 4.dp
                    )
            )
        }
    }
}
