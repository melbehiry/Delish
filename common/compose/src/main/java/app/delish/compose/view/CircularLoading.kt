package app.delish.compose.view

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CircularLoading(
    isLoading: Boolean,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Crossfade(targetState = isLoading, modifier = modifier){
        when(it){
            true -> CircularLoadingIndicator()
            false -> content()
        }
    }
}

@Composable
fun CircularLoadingIndicator() = Box(
    contentAlignment = Alignment.Center,
    modifier = Modifier.fillMaxSize()
) {
    CircularProgressIndicator(
        color = MaterialTheme.colors.primary,
        modifier = Modifier.size(40.dp)
    )
}
