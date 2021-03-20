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

package com.elbehiry.shared.network

import com.elbehiry.shared.data.remote.DelishApi
import com.elbehiry.test_shared.MainCoroutineRule
import com.elbehiry.test_shared.RecipesTestData
import com.elbehiry.test_shared.runBlockingTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

class DelishServiceTestUsingMockWebServer {

    @get:Rule
    val mockWebServer = MockWebServer()

    @get:Rule
    var coroutineRule = MainCoroutineRule()

    val moshi by lazy {
        Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    private val retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    private val delishApiService by lazy {
        retrofit.create(DelishApi::class.java)
    }

    @Test
    fun test_get_Random_Recipes() {
        mockWebServer.enqueue(
            MockResponse()
                .setBody(RecipesTestData.recipes)
                .setResponseCode(200)
        )
        coroutineRule.runBlockingTest {
            val recipes = delishApiService.getRandomRecipes(tags = null, number = null)
            Assert.assertNotNull(recipes)
            val recipeItem = recipes.recipes[0]
            Assert.assertEquals(recipeItem.id, RecipesTestData.id)
        }
    }

    @After
    fun tearDown() {
        try {
            mockWebServer.shutdown()
        } catch (exception: Exception) {
            exception.printStackTrace()
        }
    }
}
