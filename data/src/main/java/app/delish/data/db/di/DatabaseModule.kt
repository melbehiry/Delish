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

package app.delish.data.db.di

import android.content.Context
import androidx.room.Room
import app.delish.data.db.Constants
import app.delish.data.db.DelishDataBase
import app.delish.data.db.JsonConverter
import app.delish.data.db.MIGRATIONS
import app.delish.data.db.recipes.dao.CuisineDao
import app.delish.data.db.recipes.dao.IngredientDao
import app.delish.data.db.recipes.dao.RecipesDao
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
    @Suppress("SpreadOperator")
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
    fun provideRecipesTable(delishDataBase: DelishDataBase): RecipesDao {
        return delishDataBase.recipesTable
    }

    @Provides
    @Singleton
    fun provideCuisineDao(delishDataBase: DelishDataBase): CuisineDao {
        return delishDataBase.cuisineDao
    }

    @Provides
    @Singleton
    fun provideIngredientDao(delishDataBase: DelishDataBase): IngredientDao {
        return delishDataBase.ingredientDao
    }

    @Provides
    @Singleton
    fun provideJsonConverter(moshi: Moshi): JsonConverter = JsonConverter(moshi)
}
