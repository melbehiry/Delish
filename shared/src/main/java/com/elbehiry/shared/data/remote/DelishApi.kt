package com.elbehiry.shared.data.remote

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
}