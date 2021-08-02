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

import com.squareup.moshi.Json

data class VenuesResult(
    @Json(name = "response")
    val response: Response? = null
)

data class Response(

    @Json(name = "confident")
    val confident: Boolean? = null,

    @Json(name = "venues")
    val venues: List<VenuesItem>? = emptyList()
)

data class VenuesItem(

    @Json(name = "hasPerk")
    val hasPerk: Boolean? = null,

    @Json(name = "referralId")
    val referralId: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "location")
    val location: Location,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "categories")
    val categories: List<CategoriesItem?>? = null,

    @Json(name = "venuePage")
    val venuePage: VenuePage? = null
)

data class Icon(

    @Json(name = "prefix")
    val prefix: String? = null,

    @Json(name = "suffix")
    val suffix: String? = null
)

data class CategoriesItem(

    @Json(name = "pluralName")
    val pluralName: String? = null,

    @Json(name = "name")
    val name: String? = null,

    @Json(name = "icon")
    val icon: Icon? = null,

    @Json(name = "id")
    val id: String? = null,

    @Json(name = "shortName")
    val shortName: String? = null,

    @Json(name = "primary")
    val primary: Boolean? = null
)
data class LabeledLatLngsItem(

    @Json(name = "lng")
    val lng: Double? = null,

    @Json(name = "label")
    val label: String? = null,

    @Json(name = "lat")
    val lat: Double? = null
)

data class VenuePage(

    @Json(name = "id")
    val id: String? = null
)

data class Location(

    @Json(name = "cc")
    val cc: String? = null,

    @Json(name = "country")
    val country: String? = null,

    @Json(name = "address")
    val address: String? = null,

    @Json(name = "labeledLatLngs")
    val labeledLatLngs: List<LabeledLatLngsItem?>? = null,

    @Json(name = "lng")
    val lng: Double,

    @Json(name = "distance")
    val distance: Int? = null,

    @Json(name = "formattedAddress")
    val formattedAddress: List<String?>? = null,

    @Json(name = "city")
    val city: String? = null,

    @Json(name = "state")
    val state: String? = null,

    @Json(name = "crossStreet")
    val crossStreet: String? = null,

    @Json(name = "lat")
    val lat: Double,

    @Json(name = "neighborhood")
    val neighborhood: String? = null,

    @Json(name = "postalCode")
    val postalCode: String? = null
)
