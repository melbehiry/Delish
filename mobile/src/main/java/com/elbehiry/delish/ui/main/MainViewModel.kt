package com.elbehiry.delish.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.*
import com.elbehiry.delish.ui.util.IngredientListProvider
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.shared.result.data

class MainViewModel @ViewModelInject constructor(
    private val getRandomRecipesUseCase: GetRandomRecipesUseCase,
) : ViewModel() {

    private val randomRecipesParams = MutableLiveData<GetRandomRecipesUseCase.Params>()
    val ingredientList: LiveData<List<IngredientItem>> = liveData {
        emit(IngredientListProvider.ingredientList)
    }

    private val resultPhotos: LiveData<Result<List<RecipesItem>>> =
        randomRecipesParams.switchMap { params ->
            liveData {
                emit(getRandomRecipesUseCase(params))
            }
        }

    val randomRecipes: LiveData<List<RecipesItem>> = resultPhotos.map {
        it.data ?: listOf()
    }

    val isLoading: LiveData<Boolean> = resultPhotos.map {
        it == Result.Loading
    }


    fun getRandomRecipes() {
        randomRecipesParams.value = GetRandomRecipesUseCase.Params.create(null, 20)
    }
}