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

package com.elbehiry.shared.data.remote

import com.elbehiry.model.CuisineItem
import com.elbehiry.model.Recipes
import com.elbehiry.shared.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query

interface DelishApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("tags") tags: String?,
        @Query("number") number: Int?
    ): Recipes

    @GET("https://delish.getsandbox.com/getCuisines")
    suspend fun getAvailableCuisines(): List<CuisineItem>
}
