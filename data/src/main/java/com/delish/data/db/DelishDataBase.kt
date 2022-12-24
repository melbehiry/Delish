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

package com.delish.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.delish.data.db.recipes.dao.CuisineDao
import com.delish.data.db.recipes.dao.IngredientDao
import com.delish.data.db.recipes.dao.RecipesDao
import com.delish.data.db.recipes.entities.CuisineEntity
import com.delish.data.db.recipes.entities.IngredientEntity
import com.delish.data.db.recipes.entities.RecipeEntity

@Database(
    entities = [
        RecipeEntity::class,
        CuisineEntity::class,
        IngredientEntity::class,
    ],
    version = Constants.VERSION
)
internal abstract class DelishDataBase : RoomDatabase() {
    abstract val recipesTable: RecipesDao
    abstract val cuisineDao : CuisineDao
    abstract val ingredientDao : IngredientDao
}
