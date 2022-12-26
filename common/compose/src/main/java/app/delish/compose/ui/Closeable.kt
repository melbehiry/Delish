package app.delish.compose.ui

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun Closeable(
    opened: Boolean,
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.colors.background.copy(alpha = 0.3f),
    onClosed: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    BackHandler(enabled = opened, onBack = onClosed)

    AnimatedVisibility(
        modifier = modifier,
        visible = opened,
        enter = fadeIn(),
        exit = fadeOut(),
    ) {
        Box(
            Modifier
                .fillMaxSize()
                .background(backgroundColor)
                .noIndicationClick(onClick = onClosed)
        )
    }

    content()
}
