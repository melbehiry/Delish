package app.delish.compose.view.rowCell

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.LocalMinimumTouchTargetEnforcement
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope

@Composable
fun ConstraintLayoutScope.RowCellStartIcon(type: RowCellStartIconType, ref: ConstrainedLayoutReference) {
    when (type) {
        is RowCellStartIconType.IconRowCell -> {
            Icon(
                modifier = Modifier.constrainAs(ref) {
                    start.linkTo(parent.start, 16.dp)
                    top.linkTo(parent.top, 8.dp)
                    bottom.linkTo(parent.bottom, 8.dp)
                },
                painter = painterResource(id = type.iconRes),
                contentDescription = null,
                tint = type.iconTint
            )
        }
        is RowCellStartIconType.IconButtonRowCell -> {
            CompositionLocalProvider(
                LocalMinimumTouchTargetEnforcement provides false,
            ) {
                IconButton(
                    modifier = Modifier.constrainAs(ref) {
                        start.linkTo(parent.start, 16.dp)
                        top.linkTo(parent.top, 8.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    },
                    onClick = {
                        type.onClick.invoke()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = type.iconRes),
                        contentDescription = null,
                        tint = type.iconTint
                    )
                }
            }
        }
    }
}
