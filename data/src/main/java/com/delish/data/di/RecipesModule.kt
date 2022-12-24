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

package com.delish.data.di

import com.delish.data.db.recipes.dao.CuisineDao
import com.delish.data.db.recipes.dao.IngredientDao
import com.delish.data.db.recipes.dao.RecipesDao
import com.delish.data.home.local.HomeLocalDataSource
import com.delish.data.home.local.HomeLocalDataSourceImpl
import com.delish.data.home.remote.HomeDataSource
import com.delish.data.home.remote.HomeRemoteDataSource
import com.delish.data.home.repository.HomeRepository
import com.delish.data.home.repository.HomeRepositoryImpl
import com.delish.data.plan.remote.GetMealPlanRemoteDataSource
import com.delish.data.plan.remote.MealPlanDataSource
import com.delish.data.plan.repository.GetMealPlanRepository
import com.delish.data.plan.repository.MealPlanRepository
import com.delish.data.recipes.local.RecipesLocalDataSource
import com.delish.data.recipes.local.RecipesLocalDataSourceImpl
import com.delish.data.recipes.remote.RecipesDataSource
import com.delish.data.recipes.remote.SearchRecipesDataSource
import com.delish.data.recipes.repository.RecipesRepository
import com.delish.data.recipes.repository.RecipesRepositoryImpl
import com.delish.data.remote.DelishApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class RecipesModule {

    @Provides
    fun provideHomeDataSource(api: DelishApi): HomeDataSource =
        HomeRemoteDataSource(api)

    @Provides
    fun provideHomeLocalDataSource(
        recipesDao: RecipesDao,
        cuisineDao: CuisineDao,
        ingredientDao: IngredientDao
    ): HomeLocalDataSource =
        HomeLocalDataSourceImpl(recipesDao, cuisineDao, ingredientDao)

    @Provides
    fun provideHomeRepository(
        homeDataSource: HomeDataSource,
        homeLocalDataSource: HomeLocalDataSource
    ): HomeRepository =
        HomeRepositoryImpl(homeDataSource, homeLocalDataSource)

    @Provides
    fun provideRecipesDataSource(api: DelishApi): RecipesDataSource =
        SearchRecipesDataSource(api)

    @Provides
    fun provideRecipesLocalDataSource(
        recipesTable: RecipesDao
    ): RecipesLocalDataSource =
        RecipesLocalDataSourceImpl(recipesTable = recipesTable)

    @Provides
    fun provideRecipesRepository(
        recipesDataSource: RecipesDataSource,
        recipesLocalDataSource: RecipesLocalDataSource
    ): RecipesRepository =
        RecipesRepositoryImpl(recipesDataSource, recipesLocalDataSource)

    @Provides
    fun provideMealPlanDataSource(api: DelishApi): MealPlanDataSource =
        GetMealPlanRemoteDataSource(api)

    @Provides
    fun provideMealPlanRepository(
        mealPlanDataSource: MealPlanDataSource
    ): MealPlanRepository =
        GetMealPlanRepository(mealPlanDataSource)
}
