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

data class Recipes(
    val recipes: List<RecipesItem> = emptyList()
)

data class IngredientsItem(
    val image: String? = null,
    val localizedName: String? = null,
    val name: String? = null,
    val id: Int? = null
)

data class Measures(
    val metric: Metric? = null,
    val us: Us? = null
)

data class StepsItem(
    val number: Int? = null,
    val ingredients: List<IngredientsItem?>? = null,
    val equipment: List<EquipmentItem?>? = null,
    val step: String? = null,
    val length: Length? = null
)

data class EquipmentItem(
    val image: String? = null,
    val localizedName: String? = null,
    val name: String? = null,
    val id: Int? = null
)

data class AnalyzedInstructionsItem(
    val name: String? = null,
    val steps: List<StepsItem?>? = null
)

data class Length(
    val number: Int? = null,
    val unit: String? = null
)

data class Temperature(
    val number: Double? = null,
    val unit: String? = null
)

data class ExtendedIngredientsItem(
    val image: String? = null,
    val amount: Double? = null,
    val nameClean: String? = null,
    val original: String? = null,
    val aisle: String? = null,
    val consistency: String? = null,
    val originalName: String? = null,
    val unit: String? = null,
    val measures: Measures? = null,
    val meta: List<Any?>? = null,
    val name: String? = null,
    val originalString: String? = null,
    val id: Int? = null,
    val metaInformation: List<Any?>? = null
)

data class Us(
    val amount: Double? = null,
    val unitShort: String? = null,
    val unitLong: String? = null
)

data class Metric(
    val amount: Double? = null,
    val unitShort: String? = null,
    val unitLong: String? = null
)

data class RecipesItem(
    val instructions: String? = null,
    val sustainable: Boolean? = null,
    val analyzedInstructions: List<AnalyzedInstructionsItem?>? = null,
    val glutenFree: Boolean? = null,
    val veryPopular: Boolean? = null,
    val healthScore: Double? = null,
    val title: String? = null,
    val diets: List<String?>? = null,
    val aggregateLikes: Int? = null,
    val creditsText: String? = null,
    val readyInMinutes: Int? = null,
    val sourceUrl: String? = null,
    val dairyFree: Boolean? = null,
    val servings: Double? = null,
    val vegetarian: Boolean? = null,
    val id: Int? = 1,
    val imageType: String? = null,
    val summary: String? = null,
    val image: String? = null,
    val veryHealthy: Boolean? = null,
    val vegan: Boolean? = null,
    val cheap: Boolean? = null,
    val extendedIngredients: List<ExtendedIngredientsItem?>? = null,
    val dishTypes: List<String?>? = null,
    val gaps: String? = null,
    val cuisines: List<Any?>? = null,
    val lowFodmap: Boolean? = null,
    val license: String? = null,
    val weightWatcherSmartPoints: Int? = null,
    val occasions: List<Any?>? = null,
    val spoonacularScore: Double? = null,
    val pricePerServing: Double? = null,
    val sourceName: String? = null,
    val originalId: Int? = null,
    val spoonacularSourceUrl: String? = null,
    val winePairing: WinePairing? = null,
    val nutrition: Nutrition? = null,
)

data class CaloricBreakdown(
    val percentCarbs: Double? = null,
    val percentProtein: Double? = null,
    val percentFat: Double? = null
)

data class PropertiesItem(
    val amount: Double? = null,
    val unit: String? = null,
    val name: String? = null,
    val title: String? = null
)

data class FlavanoidsItem(
    val amount: Double? = null,
    val unit: String? = null,
    val name: String? = null,
    val title: String? = null
)

data class NutrientsItem(
    val amount: Double? = null,
    val unit: String? = null,
    val percentOfDailyNeeds: Double? = null,
    val name: String? = null,
    val title: String? = null
)

data class Nutrition(
    val caloricBreakdown: CaloricBreakdown? = null,
    val weightPerServing: WeightPerServing? = null,
    val ingredients: List<IngredientsItem?>? = null,
    val flavanoids: List<FlavanoidsItem?>? = null,
    val properties: List<PropertiesItem?>? = null,
    val nutrients: List<NutrientsItem?>? = null
)

data class WinePairing(
    val any: Any? = null
)

data class WeightPerServing(
    val amount: Double? = null,
    val unit: String? = null
)
