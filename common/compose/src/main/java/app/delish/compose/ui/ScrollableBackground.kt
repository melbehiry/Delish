package app.delish.compose.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Layout

@Composable
fun ScrollableBackground(
    getScrollPositionOffset: () -> Int
) = Layout(content = {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.surface)
    )
}) { measurable, constraints ->
    val placeable = measurable.first().measure(constraints)
    layout(constraints.maxWidth, constraints.maxHeight) {
        val offset = getScrollPositionOffset()
        placeable.placeRelative(x = 0, y = offset)
    }
}
