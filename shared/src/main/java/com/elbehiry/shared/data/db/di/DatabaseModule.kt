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

package com.elbehiry.shared.data.db.di

import android.content.Context
import androidx.room.Room
import com.elbehiry.shared.data.db.Constants
import com.elbehiry.shared.data.db.DelishDataBase
import com.elbehiry.shared.data.db.JsonConverter
import com.elbehiry.shared.data.db.commons.MIGRATIONS
import com.elbehiry.shared.data.db.datastore.RecipesLocalDataStore
import com.elbehiry.shared.data.db.recipes.recipedatastore.RecipeMapper
import com.elbehiry.shared.data.db.recipes.recipedatastore.RecipeMapperImpl
import com.elbehiry.shared.data.db.recipes.recipedatastore.RecipesDatabaseDataStore
import com.elbehiry.shared.data.db.recipes.tables.RecipesTable
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun provideDelishDatabase(@ApplicationContext context: Context): DelishDataBase {
        return Room.databaseBuilder(
            context,
            DelishDataBase::class.java,
            Constants.DATABASE_NAME
        ).addMigrations(*MIGRATIONS)
            .build()
    }

    @Provides
    @Singleton
    fun provideRecipesTable(delishDataBase: DelishDataBase): RecipesTable {
        return delishDataBase.recipesTable
    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): JsonConverter = JsonConverter(moshi)

    @Provides
    @Singleton
    fun provideRecipesMapper(jsonConverter: JsonConverter): RecipeMapper {
        return RecipeMapperImpl(jsonConverter)
    }

    @Provides
    @Singleton
    fun provideRecipeDataStore(
        recipesTable: RecipesTable,
        recipeMapper: RecipeMapper,
    ): RecipesLocalDataStore {
        return RecipesDatabaseDataStore(recipesTable, recipeMapper)
    }
}
