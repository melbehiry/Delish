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

package com.elbehiry.delish.di

import com.elbehiry.delish.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

const val SPOONACULAR_KEY = "SPOONACULAR_KEY"
const val SPOONACULAR_BASE_URL = "SPOONACULAR_BASE_URL"
const val INGREDIENTS_DATA_URL = "INGREDIENTS_DATA_URL"
const val CUISINES_DATA_URL = "CUISINES_DATA_URL"

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named(SPOONACULAR_KEY)
    fun provideSpoonCularKey(): String = BuildConfig.SPOONACULAR_KEY

    @Provides
    @Named(SPOONACULAR_BASE_URL)
    fun provideSpoonCularBaseUrlKey(): String = BuildConfig.SPOONACULAR_BASE_URL

    @Provides
    @Named(INGREDIENTS_DATA_URL)
    fun provideIngredientsBaseUrlKey(): String = BuildConfig.INGREDIENTS_DATA_URL

    @Provides
    @Named(CUISINES_DATA_URL)
    fun provideCuisineBaseUrlKey(): String = BuildConfig.CUISINES_DATA_URL

}
