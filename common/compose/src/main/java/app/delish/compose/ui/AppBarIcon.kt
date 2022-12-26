package app.delish.compose.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun AppBarIcon(
    imageVector: ImageVector,
    contentDescription: String,
    modifier: Modifier = Modifier,
    onClicked: (() -> Unit)? = null
) {
    Icon(
        imageVector,
        contentDescription = contentDescription,
        tint = LocalContentColor.current.copy(alpha = 0.7f),
        modifier = modifier
            .let {
                if (onClicked != null) {
                    it.clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = rememberRipple(
                            bounded = false,
                            radius = 16.dp
                        ),
                        onClick = onClicked
                    )
                } else it
            }
    )
}
