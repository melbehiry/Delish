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

package com.elbehiry.model

data class MealsPlan(
    val week: Week? = null
)

data class Week(
    val sunday: DayMeal? = null,
    val saturday: DayMeal? = null,
    val tuesday: DayMeal? = null,
    val wednesday: DayMeal? = null,
    val thursday: DayMeal? = null,
    val friday: DayMeal? = null,
    val monday: DayMeal? = null
)

data class Nutrients(
    val carbohydrates: Double? = null,
    val protein: Double? = null,
    val fat: Double? = null,
    val calories: Double? = null
)

data class DayMeal(
    val meals: List<RecipesItem>? = null,
    val nutrients: Nutrients? = null
)
