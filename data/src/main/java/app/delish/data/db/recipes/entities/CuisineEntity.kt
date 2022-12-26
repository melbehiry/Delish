package app.delish.data.db.recipes.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

private const val CUISINE_TABLE_NAME = "cuisines"

@Entity(tableName = CUISINE_TABLE_NAME)
class CuisineEntity (
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val image: String,
    val title: String,
    val color: String
)
