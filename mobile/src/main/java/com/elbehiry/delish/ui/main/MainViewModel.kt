package com.elbehiry.delish.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.elbehiry.delish.ui.util.IngredientListProvider
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.cuisines.GetAvailableCuisinesUseCase
import com.elbehiry.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
    private val getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase
) : ViewModel() {


    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _hasError = MutableLiveData<Boolean>()
    val hasError: LiveData<Boolean> = _hasError

    private val _ingredientList = MutableLiveData<List<IngredientItem>>()
    val ingredientList: LiveData<List<IngredientItem>> = _ingredientList

    private val _cuisinesList = MutableLiveData<List<CuisineItem>>()
    val cuisinesList: LiveData<List<CuisineItem>> = _cuisinesList

    private val _randomRecipes = MutableLiveData<List<RecipesItem>>()
    val randomRecipes: LiveData<List<RecipesItem>> = _randomRecipes

    fun getRandomRecipes() {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                coroutineScope {
                    val ingredientListDeferred = async { IngredientListProvider.ingredientList }
                    val cuisinesListDeferred = async { getAvailableCuisinesUseCase(Unit) }
                    val randomRecipesDeferred = async {
                        getRandomRecipesUseCase(
                            GetRandomRecipesUseCase.Params.create(
                                null,
                                20
                            )
                        )
                    }

                    val ingredientList = ingredientListDeferred.await()
                    val cuisinesList = cuisinesListDeferred.await()
                    val randomRecipes = randomRecipesDeferred.await()

                    if (cuisinesList is Result.Error || randomRecipes is Result.Error){
                        _hasError.postValue(true)
                    }

                    _randomRecipes.postValue(randomRecipes.data ?: listOf())
                    _ingredientList.postValue(ingredientList)
                    _cuisinesList.postValue(cuisinesList.data ?: listOf())
                }
            } catch (e: Exception) {
                _hasError.postValue(true)
            } finally {
                _isLoading.value = false
            }
        }
    }
}