package app.delish.data.db.recipes.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import app.delish.data.db.recipes.entities.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM DelishRecipes")
    fun getRecipes(): Flow<List<RecipeEntity>>

    @Transaction
    @Query("SELECT * FROM DelishRecipes")
    fun getPagedRecipes(): PagingSource<Int, RecipeEntity>

    @Query("SELECT * FROM DelishRecipes WHERE id=:recipeId")
    suspend fun getRecipe(recipeId: Int?): RecipeEntity?

    @Query("DELETE FROM DelishRecipes WHERE id=:recipeId")
    suspend fun deleteRecipe(recipeId: Int?)

    @Query("SELECT COUNT(*) FROM DelishRecipes WHERE id=:recipeId")
    suspend fun isRecipeSaved(recipeId: Int?): Boolean

}
