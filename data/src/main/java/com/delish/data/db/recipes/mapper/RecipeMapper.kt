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

package com.delish.data.db.recipes.mapper

import com.delish.data.db.recipes.entities.CuisineEntity
import com.delish.data.db.recipes.entities.IngredientEntity
import com.delish.data.db.recipes.entities.RecipeEntity
import com.elbehiry.model.CuisineItem
import com.elbehiry.model.IngredientItem
import com.elbehiry.model.RecipesItem

const val SEPARATOR = "_"

fun CuisineEntity.toCuisine(): CuisineItem = CuisineItem(
    image = image,
    title = title,
    color = color
)

fun CuisineItem.toCuisineEntity(): CuisineEntity = CuisineEntity(
    image = image,
    title = title,
    color = color
)

fun IngredientEntity.toIngredient(): IngredientItem = IngredientItem(
    id = id,
    image = image,
    title = title,
    background = background
)

fun IngredientItem.toIngredientEntity(): IngredientEntity = IngredientEntity(
    id = id,
    image = image,
    title = title,
    background = background
)

fun RecipesItem.toRecipeEntity() = RecipeEntity(
    id = id,
    title = title,
    sustainable = sustainable,
    glutenFree = glutenFree,
    veryPopular = veryPopular,
    vegetarian = vegetarian,
    dairyFree = dairyFree,
    veryHealthy = veryHealthy,
    vegan = vegan,
    cheap = cheap,
    spoonacularScore = spoonacularScore,
    aggregateLikes = aggregateLikes,
    sourceName = sourceName,
    creditsText = creditsText,
    readyInMinutes = readyInMinutes,
    image = image,
    percentCarbs = percentCarbs,
    percentProtein = percentProtein,
    percentFat = percentFat,
    nutrientsAmount = nutrientsAmount,
    nutrientsName = nutrientsName,
    ingredientOriginalString = ingredientOriginalString?.joinToString(SEPARATOR),
    step = step?.joinToString(SEPARATOR),
)

fun RecipeEntity.toRecipesItem() = RecipesItem(
    id = id,
    title = title,
    sustainable = sustainable,
    glutenFree = glutenFree,
    veryPopular = veryPopular,
    vegetarian = vegetarian,
    dairyFree = dairyFree,
    veryHealthy = veryHealthy,
    vegan = vegan,
    cheap = cheap,
    spoonacularScore = spoonacularScore,
    aggregateLikes = aggregateLikes,
    sourceName = sourceName,
    creditsText = creditsText,
    readyInMinutes = readyInMinutes,
    image = image,
    percentCarbs = percentCarbs,
    percentProtein = percentProtein,
    percentFat = percentFat,
    nutrientsAmount = nutrientsAmount,
    nutrientsName = nutrientsName,
    ingredientOriginalString = ingredientOriginalString?.split(SEPARATOR),
    step = step?.split(SEPARATOR),
    saved = true
    )