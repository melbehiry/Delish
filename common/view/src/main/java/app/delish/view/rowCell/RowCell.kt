package app.delish.view.rowCell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
fun RowCell(
    rowCellTextType: RowCellTextType,
    rowCellStartIconType: RowCellStartIconType? = null,
    endIcon: Painter? = null,
    endIconTint: Color = Color(0x99EBEBF5),
    onClick: () -> Unit = {},
    withDivider: Boolean = false,
    dividerColor: Color = Color(0xFF252627)
) {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                enabled = rowCellStartIconType !is RowCellStartIconType.IconButtonRowCell,
                onClick = {
                    onClick.invoke()
                }
            )
    ) {
        val (startIconRef, textRef, endIconRef, dividerRef) = createRefs()

        rowCellStartIconType?.let {
            RowCellStartIcon(rowCellStartIconType, startIconRef)
        }

        RowCellText(
            rowCellStartIconType = rowCellStartIconType,
            endIcon = endIcon,
            startIconRef = startIconRef,
            textRef = textRef,
            endIconRef = endIconRef,
            rowCellTextType = rowCellTextType
        )

        endIcon?.let { icon ->
            Icon(
                modifier = Modifier
                    .constrainAs(endIconRef) {
                        top.linkTo(parent.top, 8.dp)
                        end.linkTo(parent.end, 16.dp)
                        bottom.linkTo(parent.bottom, 8.dp)
                    },
                painter = icon,
                contentDescription = null,
                tint = endIconTint
            )
        }

        if (withDivider) {
            Divider(
                modifier = Modifier
                    .height(0.5.dp)
                    .constrainAs(dividerRef) {
                        linkTo(start = textRef.start, end = parent.end)
                        bottom.linkTo(parent.bottom)
                        width = Dimension.fillToConstraints
                    },
                color = dividerColor
            )
        }
    }
}
