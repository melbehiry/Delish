package app.delish.domain.usecases.search

import androidx.paging.PagingData
import app.delish.data.recipes.repository.RecipesRepository
import app.delish.domain.UseCase
import com.elbehiry.model.RecipesItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class SearchRecipesUseCase @Inject constructor(
    private val recipesRepository: RecipesRepository
) : UseCase<SearchRecipesUseCase.Params, Flow<PagingData<RecipesItem>>>() {

    override fun execute(parameters: Params): Flow<PagingData<RecipesItem>> {
        return recipesRepository.searchRecipes(parameters.query, parameters.cuisine)
    }

    class Params private constructor(
        val query: String?,
        val cuisine: String?
    ) {

        companion object {
            @JvmStatic
            fun create(
                query: String? = "",
                cuisine: String? = ""
            ): Params {
                return Params(query, cuisine)
            }
        }
    }
}
