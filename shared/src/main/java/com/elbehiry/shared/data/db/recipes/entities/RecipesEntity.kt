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

package com.elbehiry.shared.data.db.recipes.entities

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(
    tableName = RecipeEntity.Schema.TABLE_NAME,
    primaryKeys = [RecipeEntity.Schema.RECIPE_ID]
)
data class RecipeEntity(
    @ColumnInfo(name = Schema.RECIPE_ID) val recipeId: Int,
    @ColumnInfo(name = Schema.RECIPE_TITLE) val title: String,
    @ColumnInfo(name = Schema.SUSTAINABLE) val sustainable: Boolean,
    @ColumnInfo(name = Schema.GLUTEN_FREE) val glutenFree: Boolean,
    @ColumnInfo(name = Schema.VERY_POPULAR) val veryPopular: Boolean,
    @ColumnInfo(name = Schema.VEGETARIAN) val vegetarian: Boolean,
    @ColumnInfo(name = Schema.DAIRY_FREE) val dairyFree: Boolean,
    @ColumnInfo(name = Schema.VERY_HEALTHY) val veryHealthy: Boolean,
    @ColumnInfo(name = Schema.VEGAN) val vegan: Boolean,
    @ColumnInfo(name = Schema.CHEAP) val cheap: Boolean,
    @ColumnInfo(name = Schema.SPOON_SCORE) val spoonScore: Double,
    @ColumnInfo(name = Schema.AGGREGATE_LIKES) val aggregateLikes: Int,
    @ColumnInfo(name = Schema.SOURCE_NAME) val sourceName: String,
    @ColumnInfo(name = Schema.CREDIT_TEXT) val creditsText: String,
    @ColumnInfo(name = Schema.READY_MINUTES) val readyInMinutes: Int,
    @ColumnInfo(name = Schema.IMAGE) val image: String,
    @ColumnInfo(name = Schema.PERCENT_CARBS) val percentCarbs: Double,
    @ColumnInfo(name = Schema.PERCENT_PROTEIN) val percentProtein: Double,
    @ColumnInfo(name = Schema.PERCENT_FAT) val percentFat: Double,
    @ColumnInfo(name = Schema.NUTRIENTS_AMOUNT) val nutrientsAmount: Double,
    @ColumnInfo(name = Schema.NUTRIENTS_NAME) val nutrientsName: String,
    @ColumnInfo(name = Schema.INGREDIENT_TITLE) val ingredientOriginalString: String,
    @ColumnInfo(name = Schema.STEPS) val steps: String
) {
    object Schema {
        const val TABLE_NAME = "recipes"
        const val RECIPE_ID = "recipeId"
        const val RECIPE_TITLE = "recipeTitle"
        const val SUSTAINABLE = "sustainable"
        const val GLUTEN_FREE = "glutenFree"
        const val VERY_POPULAR = "veryPopular"
        const val VEGETARIAN = "vegetarian"
        const val DAIRY_FREE = "dairyFree"
        const val VERY_HEALTHY = "veryHealthy"
        const val VEGAN = "vegan"
        const val CHEAP = "cheap"
        const val AGGREGATE_LIKES = "aggregateLikes"
        const val SOURCE_NAME = "sourceName"
        const val CREDIT_TEXT = "creditsText"
        const val READY_MINUTES = "readyInMinutes"
        const val IMAGE = "image"
        const val INGREDIENT_TITLE = "ingredientOriginalString"
        const val STEPS = "steps"
        const val PERCENT_CARBS = "percentCarbs"
        const val PERCENT_PROTEIN = "percentProtein"
        const val PERCENT_FAT = "percentFat"
        const val NUTRIENTS_AMOUNT = "nutrientsAmount"
        const val NUTRIENTS_NAME = "nutrientsName"
        const val SPOON_SCORE = "spoonScore"
    }
}
