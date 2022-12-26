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

package com.elbehiry.shared.data.searchrestaurants
//
//import app.cash.turbine.test
//import com.elbehiry.model.VenuesResult
//import com.elbehiry.shared.data.restaurants.remote.ISearchRestaurantsDataSource
//import com.elbehiry.shared.data.restaurants.repository.ISearchRestaurantsRepository
//import com.elbehiry.shared.data.restaurants.repository.SearchRestaurantsRepository
//import com.elbehiry.shared.result.data
//import com.elbehiry.test_shared.MainCoroutineRule
//import com.elbehiry.test_shared.SEARCH_ITEM
//import com.elbehiry.test_shared.VENUES_ITEMS
//import com.elbehiry.test_shared.runBlockingTest
//import com.github.javafaker.Faker
//import com.nhaarman.mockito_kotlin.any
//import com.nhaarman.mockito_kotlin.whenever
//import org.junit.Assert
//import org.junit.Before
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class SearchRepositoryTest {
//
//    @get:Rule
//    var coroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var searchDataSource: ISearchRestaurantsDataSource
//
//    private lateinit var searchRepository: ISearchRestaurantsRepository
//    private val faker by lazy {
//        Faker()
//    }
//
//    @Before
//    fun setup() {
//        searchRepository = SearchRestaurantsRepository(searchDataSource)
//    }
//
//    @Test
//    fun `test search should be successful`() {
//        coroutineRule.runBlockingTest {
//            whenever(
//                searchDataSource.search(
//                    any(), any(), any(), any()
//                )
//            ).thenReturn(
//                SEARCH_ITEM
//            )
//
//            searchRepository.search(
//                "${faker.address().latitude()},${faker.address().longitude()}",
//                faker.number().digits(3).toString(),
//                faker.number().digits(2).toInt(),
//                faker.number().digits(2).toInt()
//            ).test {
//                Assert.assertEquals(expectItem().data, VENUES_ITEMS)
//                expectComplete()
//            }
//        }
//    }
//
//    @Test
//    fun `test search in case of empty response return empty venues`() {
//        coroutineRule.runBlockingTest {
//            whenever(
//                searchDataSource.search(
//                    any(), any(), any(), any()
//                )
//            ).thenReturn(
//                VenuesResult()
//            )
//            searchRepository.search(
//                "${faker.address().latitude()},${faker.address().longitude()}",
//                faker.number().digits(3).toString(),
//                faker.number().digits(2).toInt(),
//                faker.number().digits(2).toInt()
//            ).test {
//                Assert.assertTrue((expectItem().data!!.isEmpty()))
//                expectComplete()
//            }
//        }
//    }
//}
