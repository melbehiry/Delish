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

package com.elbehiry.delish.search

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagingData
import app.cash.turbine.test
import com.elbehiry.delish.ui.search.SearchRecipesViewModel
import com.elbehiry.delish.ui.search.SearchType
import com.elbehiry.shared.domain.recipes.search.SearchRecipesUseCase
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.RECIPE_ITEM
import com.elbehiry.test_shared.runBlockingTest
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import kotlinx.coroutines.flow.flowOf
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchRecipesViewModelTest {
    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @MockK
    private lateinit var searchRecipesUseCase: SearchRecipesUseCase

    private lateinit var searchRecipesViewModel: SearchRecipesViewModel

    @Before
    fun setup() {
        MockKAnnotations.init(this, relaxUnitFun = true)
        coEvery { searchRecipesUseCase(any()) } returns
            flowOf(PagingData.from(listOf(RECIPE_ITEM)))
        searchRecipesViewModel = SearchRecipesViewModel(searchRecipesUseCase)
    }

    @Test
    fun `search for quote should emits ui state success`() = mainCoroutineRule.runBlockingTest {
        searchRecipesViewModel.searchRecipes("test key", SearchType.QUERY).test {
            assertThat(expectItem()).isNotNull
            expectComplete()
        }
    }
}
