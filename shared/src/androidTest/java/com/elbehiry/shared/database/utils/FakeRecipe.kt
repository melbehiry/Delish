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

import com.elbehiry.shared.data.db.recipes.entities.RecipeEntity
import com.github.javafaker.Faker

private val faker: Faker by lazy {
    Faker()
}

internal val RECIPE_FAKE = RecipeEntity(
    recipeId = faker.number().digit().toInt(),
    title = faker.lorem().sentence(),
    sustainable = false,
    glutenFree = false,
    veryPopular = false,
    vegetarian = false,
    dairyFree = false,
    veryHealthy = false,
    vegan = false,
    cheap = false,
    spoonScore = faker.number().digit().toDouble(),
    aggregateLikes = faker.number().digit().toInt(),
    sourceName = faker.lorem().word(),
    creditsText = faker.lorem().sentence(),
    readyInMinutes = faker.number().digit().toInt(),
    image = faker.internet().image(),
    percentCarbs = faker.number().digit().toDouble(),
    percentProtein = faker.number().digit().toDouble(),
    percentFat = faker.number().digit().toDouble(),
    nutrientsAmount = faker.number().digit().toDouble(),
    nutrientsName = faker.lorem().word(),
    ingredientOriginalString = faker.lorem().sentence(),
    steps = faker.lorem().paragraph()
)
