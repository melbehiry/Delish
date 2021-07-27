package com.elbehiry.test_shared

import com.elbehiry.model.*

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