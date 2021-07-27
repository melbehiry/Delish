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

import com.elbehiry.model.*
import com.elbehiry.shared.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Path

const val foodDefaultCategoryId = "4d4b7105d754a06374d81259"

interface DelishApi {

    @GET("recipes/random")
    suspend fun getRandomRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("tags") tags: String?,
        @Query("number") number: Int?
    ): Recipes

    @GET(BuildConfig.CUISINES_DATA_URL)
    suspend fun getAvailableCuisines(): List<CuisineItem>

    @GET(BuildConfig.INGREDIENTS_DATA_URL)
    suspend fun getIngredients(): List<IngredientItem>

    @GET("recipes/{id}/information")
    suspend fun getRecipeInformation(
        @Path("id") id: Int?,
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("includeNutrition") includeNutrition: Boolean? = true
    ): Recipe

    @GET("/recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("query") query: String?,
        @Query("cuisine") cuisine: String?,
        @Query("addRecipeInformation") addRecipeInformation: Boolean?,
        @Query("number") number: Int?,
        @Query("offset") offset: Int
    ): SearchItem

    @GET("/mealplanner/generate")
    suspend fun getMealsPlan(
        @Query("apiKey") apiKey: String = BuildConfig.SPOONACULAR_KEY,
        @Query("timeFrame") timeFrame: String = "week"
    ): MealsPlan

    @GET(BuildConfig.FOURSQUARE_Search_Url)
    suspend fun search(
        @Query("ll") latLng: String,
        @Query("v") version: String,
        @Query("client_id") clientId: String = BuildConfig.FOURSQUARE_CLIENT_ID,
        @Query("client_secret") clientSecret: String = BuildConfig.FOURSQUARE_SECRET_ID,
        @Query("radius") radius: Int,
        @Query("limit") limit: Int,
        @Query("categoryId") categoryId: String = foodDefaultCategoryId
    ): VenuesResult
}
