package app.delish.data.db.recipes.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import app.delish.data.db.recipes.entities.CuisineEntity

@Dao
interface CuisineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCuisines(cuisines: List<CuisineEntity>)

    @Query("SELECT * FROM cuisines")
    suspend fun getCuisines(): List<CuisineEntity>

    @Query("SELECT * FROM cuisines WHERE id=:id")
    suspend fun getCuisineById(id: String): CuisineEntity
}
