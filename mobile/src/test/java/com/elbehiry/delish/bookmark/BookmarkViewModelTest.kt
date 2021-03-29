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

package com.elbehiry.delish.bookmark

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import app.cash.turbine.test
import com.elbehiry.delish.ui.bookmark.BookmarkViewModel
import com.elbehiry.shared.domain.recipes.bookmark.DeleteRecipeSuspendUseCase
import com.elbehiry.shared.domain.recipes.bookmark.GetSavedRecipesUseCase
import com.elbehiry.shared.result.Result
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.RECIPES_ITEMS
import com.elbehiry.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Rule
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import io.mockk.coEvery
import io.mockk.mockk
import java.lang.Exception

class BookmarkViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var getSavedRecipesUseCase: GetSavedRecipesUseCase

    @MockK
    private lateinit var deleteRecipeUseCase: DeleteRecipeSuspendUseCase

    private lateinit var bookmarkViewModel: BookmarkViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        getSavedRecipesUseCase = mockk()
        deleteRecipeUseCase = mockk()
    }

    @Test
    fun `get saved recipes from database should emits ui state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Success(RECIPES_ITEMS))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEqualTo(RECIPES_ITEMS)
                assertThat(expectedItem.isEmpty).isFalse()
            }
        }

    @Test
    fun `empty recipes from database should emits ui empty state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Success(emptyList()))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEmpty()
                assertThat(expectedItem.isEmpty).isTrue()
            }
        }

    @Test
    fun `failed data from database should emits ui empty state`() =
        mainCoroutineRule.runBlockingTest {
            coEvery { getSavedRecipesUseCase(Unit) } returns flowOf(Result.Error(Exception("")))

            bookmarkViewModel = BookmarkViewModel(
                getSavedRecipesUseCase,
                deleteRecipeUseCase
            )

            bookmarkViewModel.state.test {
                val expectedItem = expectItem()
                assertThat(expectedItem.recipes).isEmpty()
                assertThat(expectedItem.isEmpty).isTrue()
            }
        }
}
