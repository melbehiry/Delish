package app.delish.view.rowCell

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension

@Composable
fun ConstraintLayoutScope.RowCellText(
    rowCellStartIconType: RowCellStartIconType? = null,
    endIcon: Painter? = null,
    startIconRef: ConstrainedLayoutReference,
    textRef: ConstrainedLayoutReference,
    endIconRef: ConstrainedLayoutReference,
    rowCellTextType: RowCellTextType
) {
    Box(
        modifier = Modifier
            .constrainAs(textRef) {
                linkTo(
                    start = rowCellStartIconType?.let {
                        startIconRef.end
                    } ?: run {
                        parent.start
                    },
                    startMargin = 16.dp,
                    end = endIcon?.let {
                        endIconRef.start
                    } ?: run {
                        parent.end
                    },
                    endMargin = endIcon?.let {
                        8.dp
                    } ?: run {
                        16.dp
                    }
                )
                linkTo(top = parent.top, topMargin = 8.dp, bottom = parent.bottom, bottomMargin = 8.dp)
                width = Dimension.fillToConstraints
            }
    ) {

        when (rowCellTextType) {
            is RowCellTextType.OnlyLabel -> OnlyLabel(rowCellTextType)
            is RowCellTextType.WithCaption -> WithCaption(rowCellTextType)
            is RowCellTextType.WithTitle -> Unit
        }
    }
}

@Composable
private fun OnlyLabel(type: RowCellTextType.OnlyLabel) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = type.value?.let { Arrangement.SpaceBetween } ?: run { Arrangement.Start }
    ) {
        Text(
            text = type.label,
            style = MaterialTheme.typography.body1,
            color = Color.White,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        type.value?.let {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = type.value,
                color = Color(0x99EBEBF5),
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun WithCaption(type: RowCellTextType.WithCaption) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = type.value?.let { Arrangement.SpaceBetween } ?: run { Arrangement.Start }
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = type.label,
                style = MaterialTheme.typography.body1,
                color = Color(0xFFFFFFFF),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = type.caption,
                style = MaterialTheme.typography.body2,
                color =  Color(0x99EBEBF5),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        type.value?.let {
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = type.value,
                color =  Color(0x99EBEBF5),
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
