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

package com.elbehiry.shared.di

import com.elbehiry.shared.data.recipes.cuisines.remote.GetCuisinesDataSource
import com.elbehiry.shared.data.recipes.cuisines.remote.GetCuisinesRemoteDataSource
import com.elbehiry.shared.data.recipes.cuisines.repository.CuisinesRepository
import com.elbehiry.shared.data.recipes.cuisines.repository.GetCuisinesRepository
import com.elbehiry.shared.data.recipes.info.remote.RecipeInformationDataSource
import com.elbehiry.shared.data.recipes.info.remote.RecipeInformationRemoteDataSource
import com.elbehiry.shared.data.recipes.info.repository.GetRecipeInformationRepository
import com.elbehiry.shared.data.recipes.info.repository.RecipeInformationRepository
import com.elbehiry.shared.data.recipes.random.remote.GetRandomRecipesRemoteDataSource
import com.elbehiry.shared.data.recipes.random.remote.RandomRecipesRemoteDataSource
import com.elbehiry.shared.data.recipes.random.repository.GetRandomRecipesRepository
import com.elbehiry.shared.data.recipes.random.repository.RandomRecipesRepository
import com.elbehiry.shared.data.remote.DelishApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RecipesModule {

    @Provides
    fun provideRandomRecipesDataSource(api: DelishApi): RandomRecipesRemoteDataSource =
        GetRandomRecipesRemoteDataSource(api)

    @Provides
    fun provideRandomRecipesRepository(
        randomRecipesRemoteDataSource: RandomRecipesRemoteDataSource
    ): RandomRecipesRepository =
        GetRandomRecipesRepository(randomRecipesRemoteDataSource)

    @Provides
    fun provideGetCuisinesDataSource(api: DelishApi): GetCuisinesDataSource =
        GetCuisinesRemoteDataSource(api)

    @Provides
    fun provideCuisinesRepository(
        getCuisinesDataSource: GetCuisinesDataSource
    ): CuisinesRepository =
        GetCuisinesRepository(getCuisinesDataSource)

    @Provides
    fun provideRecipeInformationDataSource(api: DelishApi): RecipeInformationDataSource =
        RecipeInformationRemoteDataSource(api)

    @Provides
    fun provideRecipeInformationRepository(
        recipeInformationDataSource: RecipeInformationDataSource
    ): RecipeInformationRepository =
        GetRecipeInformationRepository(recipeInformationDataSource)
}
