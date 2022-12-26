package app.delish.discover.sheet

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.HorizontalRule
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import app.delish.discover.R
import app.delish.compose.ui.AsyncImage
import com.elbehiry.model.IngredientItem

@Composable
fun IngredientsDetailsSheet(
    navController: NavController,
    onIngredientSelected: (IngredientItem) -> Unit
) {
    BackHandler {
        navController.navigateUp()
    }

    IngredientsDetailsSheet(
        viewModel = hiltViewModel(),
        onIngredientSelected = onIngredientSelected
    )
}

@Composable
internal fun IngredientsDetailsSheet(
    viewModel: IngredientsViewModel,
    onIngredientSelected: (IngredientItem) -> Unit
) {
    val ingredients by viewModel.currentIngredients.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .fillMaxHeight(0.90f)
            .background(Color.DarkGray)
    ) {
        Image(
            imageVector = Icons.Filled.HorizontalRule,
            contentDescription = "arrow_down",
            modifier = Modifier
                .padding(4.dp)
                .align(Alignment.CenterHorizontally),
            colorFilter = ColorFilter.tint(Color.White)
        )

        Text(
            text = stringResource(id = R.string.ingredients),
            style = MaterialTheme.typography.h6,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(bottom = 16.dp, top = 4.dp)
                .fillMaxWidth()
        )
        LazyVerticalGrid(columns = GridCells.Fixed(2), modifier = Modifier.fillMaxSize()) {
            items(ingredients) { ingredient ->
                FullIngredientItem(ingredient, onIngredientSelected)
            }
        }
    }
}

@Composable
fun FullIngredientItem(
    ingredientItem: IngredientItem,
    onIngredientSearch: (IngredientItem) -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .padding(8.dp)
    ) {
        Box(
            modifier = Modifier
                .clip(RoundedCornerShape(8.dp))
                .background(Color(android.graphics.Color.parseColor(ingredientItem.background)))
                .clickable(
                    onClick = {
                        onIngredientSearch(ingredientItem)
                    }
                )
        ) {
            AsyncImage(
                model = ingredientItem.image,
                requestBuilder = { crossfade(true) },
                contentDescription = "Cuisine image",
                modifier = Modifier
                    .height(120.dp)
                    .width(120.dp)
                    .padding(20.dp),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            text = ingredientItem.title,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier.padding(top = 4.dp, bottom = 16.dp)
        )
    }
}
