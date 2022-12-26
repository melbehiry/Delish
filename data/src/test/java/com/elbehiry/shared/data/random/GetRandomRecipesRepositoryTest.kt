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

//import com.elbehiry.model.Recipes
//import com.elbehiry.model.Recipe
//import com.elbehiry.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource
//import com.elbehiry.shared.data.recipes.random.repository.GetRandomRecipesRepository
//import com.elbehiry.test_shared.MainCoroutineRule
//import com.elbehiry.test_shared.runBlockingTest
//import com.github.javafaker.Faker
//import com.nhaarman.mockito_kotlin.anyOrNull
//import com.nhaarman.mockito_kotlin.whenever
//import org.junit.Assert
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//import org.mockito.Mock
//import org.mockito.junit.MockitoJUnitRunner
//
//@RunWith(MockitoJUnitRunner::class)
//class GetRandomRecipesRepositoryTest {
//
//    @get:Rule
//    var coroutineRule = MainCoroutineRule()
//
//    @Mock
//    private lateinit var randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
//    private lateinit var randomRecipesRepository: GetRandomRecipesRepository
//
//    @Test
//    fun getRandomRecipesTest() {
//        coroutineRule.runBlockingTest {
//            val fakerRecipeItem = Recipe(
//                id = Faker().number().digit().toInt()
//            )
//            val recipe = Recipes(listOf(fakerRecipeItem))
//
//            whenever(randomRecipesRemoteDataSource.getRandomRecipes(anyOrNull(), anyOrNull()))
//                .thenReturn(recipe)
//
//            randomRecipesRepository = GetRandomRecipesRepository(randomRecipesRemoteDataSource)
//            val recipes = randomRecipesRepository.getRandomRecipes(anyOrNull(), anyOrNull())
//            Assert.assertEquals(recipes[0].id, fakerRecipeItem.id)
//        }
//    }
//}
