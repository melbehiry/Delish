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

package com.elbehiry.shared.database.utils

import com.elbehiry.model.RecipesItem
import com.elbehiry.shared.data.db.recipes.entities.RecipeEntity
import com.elbehiry.shared.data.db.recipes.mapper.RecipeMapper

internal class FakeRecipeMapper : RecipeMapper {

    override fun mapToDataBaseRecipe(recipesItem: RecipesItem): RecipeEntity =
        RecipeEntity(
            recipesItem.id ?: 0,
            recipesItem.title ?: "",
            recipesItem.sustainable ?: false,
            recipesItem.glutenFree ?: false,
            recipesItem.veryPopular ?: false,
            recipesItem.vegetarian ?: false,
            recipesItem.dairyFree ?: false,
            recipesItem.veryHealthy ?: false,
            recipesItem.vegan ?: false,
            recipesItem.cheap ?: false,
            recipesItem.spoonacularScore ?: 0.0,
            recipesItem.aggregateLikes ?: 0,
            recipesItem.sourceName ?: "",
            recipesItem.creditsText ?: "",
            recipesItem.readyInMinutes ?: 0,
            recipesItem.image ?: "",
            recipesItem.percentCarbs ?: 0.0,
            recipesItem.percentProtein ?: 0.0,
            recipesItem.percentFat ?: 0.0,
            recipesItem.nutrientsAmount ?: 0.0,
            recipesItem.nutrientsName ?: "",
            "", ""
        )

    override fun mapToDataRecipe(recipeEntity: RecipeEntity): RecipesItem =
        RecipesItem(
            recipeEntity.recipeId,
            recipeEntity.sustainable,
            recipeEntity.glutenFree,
            recipeEntity.veryPopular,
            recipeEntity.nutrientsAmount,
            recipeEntity.title,
            recipeEntity.aggregateLikes,
            recipeEntity.creditsText,
            recipeEntity.readyInMinutes,
            recipeEntity.dairyFree,
            recipeEntity.vegetarian,
            recipeEntity.image,
            recipeEntity.veryHealthy,
            recipeEntity.vegan,
            recipeEntity.cheap,
            recipeEntity.spoonScore,
            recipeEntity.sourceName,
            recipeEntity.percentCarbs,
            recipeEntity.percentProtein,
            recipeEntity.percentFat,
            recipeEntity.nutrientsAmount,
            recipeEntity.nutrientsName,
            emptyList(),
            emptyList()
        )
}
