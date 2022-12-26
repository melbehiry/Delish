package app.delish.compose.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable

object AppBarAlphas {
    @Composable
    fun translucentBarAlpha(): Float = when {
        // We use a more opaque alpha in light theme
        MaterialTheme.colors.isLight -> 0.97f
        else -> 0.94f
    }
}
