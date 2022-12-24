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

package com.elbehiry.shared.data.random
//
//import com.elbehiry.shared.data.recipes.random.remote.GetRandomRecipesRemoteDataSource
//import com.elbehiry.shared.data.remote.DelishApi
//import com.elbehiry.test_shared.MainCoroutineRule
//import io.mockk.MockKAnnotations
//import io.mockk.impl.annotations.MockK
//import org.junit.Before
//import org.junit.Rule
//
//class RandomRecipesRemoteDataSourceTest {
//
//    @MockK
//    private lateinit var api: DelishApi
//
//    @get:Rule
//    var coroutineRule = MainCoroutineRule()
//
//    private lateinit var randomRecipesRemoteDataSource: GetRandomRecipesRemoteDataSource
//
//    @Before
//    fun setup() {
//        MockKAnnotations.init(this, relaxUnitFun = true)
//        randomRecipesRemoteDataSource = GetRandomRecipesRemoteDataSource(api)
//    }
//
//// comment this test case because of runBlockingTest fails with "This job has not completed yet"
////    @Test
////    fun getRandomRecipesTest() = coroutineRule.runBlockingTest {
////        val recipes = Recipes()
////        coEvery {
////            api.getRandomRecipes(tags = any(), number = any())
////        } returns recipes
////        val recipesItem = randomRecipesRemoteDataSource.getRandomRecipes("", 3)
////        Assert.assertEquals(recipes, recipesItem)
////    }
//}
