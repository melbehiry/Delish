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

package com.elbehiry.shared.usecase.searchrestaurants

//import android.accounts.NetworkErrorException
//import app.cash.turbine.test
//import com.elbehiry.shared.data.restaurants.repository.ISearchRestaurantsRepository
//import app.delish.domain.usecases.SearchRestaurantsUseCase
//import com.elbehiry.shared.result.Result
//import com.elbehiry.shared.result.data
//import com.elbehiry.test_shared.MainCoroutineRule
//import com.elbehiry.test_shared.VENUES_ITEMS
//import com.elbehiry.test_shared.runBlockingTest
//import com.github.javafaker.Faker
//import com.nhaarman.mockito_kotlin.any
//import com.nhaarman.mockito_kotlin.whenever
//import kotlinx.coroutines.flow.flowOf
//import org.assertj.core.api.Assertions.assertThat
//import org.junit.Assert
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class SearchRestaurantsUseCaseTest {
//    @get:Rule
//    var coroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var searchRepository: ISearchRestaurantsRepository
//    private lateinit var searchRestaurantsUseCase: SearchRestaurantsUseCase
//
//    private val faker by lazy {
//        Faker()
//    }
//
//    @Test
//    fun `search for restaurant returns data as Result_Success value`() =
//        coroutineRule.runBlockingTest {
//            whenever(searchRepository.search(any(), any(), any(), any())).thenReturn(
//                flowOf(Result.Success(VENUES_ITEMS))
//            )
//
//            searchRestaurantsUseCase = SearchRestaurantsUseCase(
//                searchRepository, coroutineRule.testDispatcher
//            )
//
//            searchRestaurantsUseCase(createDummyParams()).test {
//                Assert.assertEquals(expectItem().data, VENUES_ITEMS)
//                expectComplete()
//            }
//        }
//
//    @Test
//    fun `search failed for restaurant returns data as Result_Error value`() =
//        coroutineRule.runBlockingTest {
//            whenever(searchRepository.search(any(), any(), any(), any())).thenReturn(
//                flowOf(Result.Error(NetworkErrorException("Network Failure")))
//            )
//
//            searchRestaurantsUseCase = SearchRestaurantsUseCase(
//                searchRepository, coroutineRule.testDispatcher
//            )
//
//            searchRestaurantsUseCase(createDummyParams()).test {
//                assertThat((expectItem() as Result.Error).exception is NetworkErrorException)
//                expectComplete()
//            }
//        }
//
//    private fun createDummyParams() = SearchRestaurantsUseCase.Params.create(
//        "${faker.address().latitude()},${faker.address().longitude()}",
//        faker.number().digits(3).toString(),
//        faker.number().digits(2).toInt(),
//        faker.number().digits(2).toInt()
//    )
//}
