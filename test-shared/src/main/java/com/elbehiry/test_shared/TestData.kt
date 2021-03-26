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

package com.elbehiry.test_shared

import com.elbehiry.model.CuisineItem
import com.elbehiry.model.RecipesItem
import com.github.javafaker.Faker

val RECIPE_ID = Faker().number().digits(3).toInt()
val recipe = "{id:$RECIPE_ID,vegetarian:true}"
val recipes = "{recipes:[$recipe]}"

val faker = Faker()

val RECIPE_ITEM = RecipesItem(
    id = faker.number().digits(3).toInt(),
    title = faker.lorem().sentence(),
    sustainable = false,
    glutenFree = false,
    veryPopular = false,
    vegetarian = false,
    dairyFree = false,
    veryHealthy = false,
    vegan = false,
    cheap = false,
    spoonacularScore = faker.number().digit().toDouble(),
    aggregateLikes = faker.number().digit().toInt(),
    sourceName = faker.lorem().word(),
    creditsText = faker.lorem().sentence(),
    readyInMinutes = faker.number().digit().toInt(),
    image = faker.internet().image(),
    percentCarbs = faker.number().digit().toDouble(),
    percentProtein = faker.number().digit().toDouble(),
    percentFat = faker.number().digit().toDouble(),
    nutrientsAmount = faker.number().digit().toDouble(),
    nutrientsName = faker.lorem().word()
)

val RECIPES_ITEMS = listOf(
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt()),
    RECIPE_ITEM.copy(id = faker.number().digits(3).toInt())
)

val CUISINE_ITEM = CuisineItem(
    image = faker.internet().image(),
    title = faker.lorem().sentence(),
    color = faker.color().hex()
)

val CUISINES_ITEMS = listOf(CUISINE_ITEM, CUISINE_ITEM, CUISINE_ITEM)