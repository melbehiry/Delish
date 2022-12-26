package app.delish.data.db.recipes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.delish.data.db.recipes.entities.IngredientEntity

@Dao
interface IngredientDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertIngredients(comments: List<IngredientEntity>)

    @Query("SELECT * FROM ingredients")
    suspend fun getIngredients(): List<IngredientEntity>

    @Query("SELECT * FROM ingredients WHERE id=:id")
    suspend fun getIngredientById(id: String): IngredientEntity

}
