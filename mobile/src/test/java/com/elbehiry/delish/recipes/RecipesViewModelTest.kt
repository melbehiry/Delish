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

package com.elbehiry.delish.recipes

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.elbehiry.delish.ui.recipes.RecipesViewModel
import com.elbehiry.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.IsRecipeSavedSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.SaveRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.cuisines.GetAvailableCuisinesUseCase
import com.elbehiry.shared.domain.recipes.ingredients.GetIngredientsUseCase
import com.elbehiry.shared.domain.recipes.random.GetRandomRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.RECIPES_ITEMS
import com.elbehiry.test_shared.INGREDIENTS
import com.elbehiry.test_shared.CUISINES_ITEMS
import com.elbehiry.test_shared.RECIPE_ITEM
import com.elbehiry.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.coVerify
import org.assertj.core.api.Assertions.assertThat
import io.mockk.impl.annotations.MockK
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class RecipesViewModelTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getRandomRecipesUseCase: GetRandomRecipesUseCase

    @MockK
    private lateinit var getAvailableCuisinesUseCase: GetAvailableCuisinesUseCase

    @MockK
    private lateinit var saveRecipeUseCase: SaveRecipeSuspendUseCase

    @MockK
    private lateinit var deleteRecipeUseCase: DeleteRecipeSuspendUseCase

    @MockK
    private lateinit var isRecipeSavedUseCase: IsRecipeSavedSuspendUseCase

    @MockK
    private lateinit var getIngredientsUseCase: GetIngredientsUseCase

    private lateinit var recipesViewModel: RecipesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { getRandomRecipesUseCase(any()) } returns flowOf(Result.Success(RECIPES_ITEMS))
        coEvery { getAvailableCuisinesUseCase(Unit) } returns flowOf(Result.Success(CUISINES_ITEMS))
        coEvery { getIngredientsUseCase(Unit) } returns flowOf(Result.Success(INGREDIENTS))

        saveRecipeUseCase = mockk()
        deleteRecipeUseCase = mockk()
        isRecipeSavedUseCase = mockk()

        recipesViewModel = RecipesViewModel(
            getRandomRecipesUseCase,
            getAvailableCuisinesUseCase,
            saveRecipeUseCase,
            deleteRecipeUseCase,
            isRecipeSavedUseCase,
            getIngredientsUseCase
        )
    }

    @Test
    fun `get content emits loading state successfully`() = mainCoroutineRule.runBlockingTest {
        recipesViewModel.getHomeContent()
        recipesViewModel.loading.test {
            assertThat(expectItem()).isNotNull()
        }
    }

    @Test
    fun `get content emits random recipes successfully`() = mainCoroutineRule.runBlockingTest {
        recipesViewModel.getHomeContent()
        recipesViewModel.viewState.test {
            assertThat(expectItem().randomRecipes).isEqualTo(RECIPES_ITEMS)
        }
    }

    @Test
    fun `get content emits cuisines list successfully`() = mainCoroutineRule.runBlockingTest {
        recipesViewModel.getHomeContent()
        recipesViewModel.viewState.test {
            assertThat(expectItem().cuisinesList).isEqualTo(CUISINES_ITEMS)
        }
    }

    @Test
    fun `get content emits ingredient list successfully`() = mainCoroutineRule.runBlockingTest {
        recipesViewModel.getHomeContent()
        recipesViewModel.viewState.test {
            assertThat(expectItem().ingredientList).isEqualTo(INGREDIENTS)
        }
    }

    @Test
    fun `get content emits error state when get ingredients use case failed`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getIngredientsUseCase(Unit) } returns flow { throw Exception("error") }
            recipesViewModel.getHomeContent()
            recipesViewModel.hasError.test {
                assertThat(expectItem()).isTrue()
            }
        }

    @Test
    fun `get content success should not emits error`() = mainCoroutineRule.runBlockingTest {
        recipesViewModel.getHomeContent()
        recipesViewModel.hasError.test {
            assertThat(expectItem()).isFalse()
        }
    }

    @Test
    fun `on book mark for not saved value should call save item use case`() {
        coEvery { isRecipeSavedUseCase(any()) } returns Result.Success(false)
        recipesViewModel.onBookMark(RECIPE_ITEM)
        coVerify { saveRecipeUseCase(RECIPE_ITEM) }
    }

    @Test
    fun `on book mark for saved value should remove item with item id`() {
        coEvery { isRecipeSavedUseCase(any()) } returns Result.Success(true)
        recipesViewModel.onBookMark(RECIPE_ITEM)
        coVerify { deleteRecipeUseCase(RECIPE_ITEM.id) }
    }

    @Test
    fun `get content emits error state when get random use case failed`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getRandomRecipesUseCase(any()) } returns flow { throw Exception("error") }
            recipesViewModel.getHomeContent()
            recipesViewModel.hasError.test {
                assertThat(expectItem()).isTrue()
            }
        }

    @Test
    fun `get content emits error state when get cuisines use case failed`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getAvailableCuisinesUseCase(Unit) } returns flow { throw Exception("error") }
            recipesViewModel.getHomeContent()
            recipesViewModel.hasError.test {
                assertThat(expectItem()).isTrue()
            }
        }
}
