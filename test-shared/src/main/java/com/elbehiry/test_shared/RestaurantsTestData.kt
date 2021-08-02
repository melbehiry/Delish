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

import com.elbehiry.model.Location
import com.elbehiry.model.VenuesItem
import com.elbehiry.model.VenuesResult
import com.elbehiry.model.Response

val LOCATION by lazy {
    Location(
        country = faker.address().country(),
        lng = faker.address().longitude().toDouble(),
        lat = faker.address().latitude().toDouble(),
        city = faker.address().city()
    )
}

val VENUES_ITEM by lazy {
    VenuesItem(
        id = faker.number().digits(3).toString(),
        referralId = faker.number().digits(2).toString(),
        name = faker.lorem().sentence(),
        location = LOCATION.copy(
            country = faker.address().country()
        )
    )
}

val VENUES_ITEMS by lazy {
    listOf(
        VENUES_ITEM.copy(id = faker.number().digits(3).toString()),
        VENUES_ITEM.copy(id = faker.number().digits(3).toString()),
        VENUES_ITEM.copy(id = faker.number().digits(3).toString()),
        VENUES_ITEM.copy(id = faker.number().digits(3).toString())
    )
}

val SEARCH_ITEM by lazy {
    VenuesResult(
        response = RESPONSE
    )
}

val RESPONSE by lazy {
    Response(
        venues = VENUES_ITEMS
    )
}
