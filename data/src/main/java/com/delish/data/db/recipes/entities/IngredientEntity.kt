package com.delish.data.db.recipes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

private const val INGREDIENTS_TABLE_NAME = "ingredients"

@Entity(tableName = INGREDIENTS_TABLE_NAME)
class IngredientEntity(
    @PrimaryKey
    val id: Int,
    val title: String,
    val image: String,
    val background: String
)