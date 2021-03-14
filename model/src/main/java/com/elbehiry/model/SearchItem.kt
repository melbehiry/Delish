package com.elbehiry.model

data class SearchItem(
    val results: List<RecipesItem>,
    val offset: Int,
    val number: Int,
    val totalResults: Int
)
