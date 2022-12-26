package app.delish.compose.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun SwipeVerticalToDismiss(
    visible: Boolean,
    modifier: Modifier = Modifier,
    onClose: () -> Unit = {},
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically { fullHeight -> fullHeight },
        exit = slideOutVertically { fullHeight -> fullHeight },
        modifier = modifier
    ) {
        val swipeVerticalState = rememberSwipeVerticalState()
        SwipeVertical(
            state = swipeVerticalState,
            onSwipeTriggered = onClose,
        ) {
            Box(
                modifier = Modifier
                    .graphicsLayer {
                        this.translationY = swipeVerticalState.offset
                    }
            ) {
                content()
            }
        }
    }
}
