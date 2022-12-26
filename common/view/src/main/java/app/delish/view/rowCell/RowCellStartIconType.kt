package app.delish.view.rowCell

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

sealed class RowCellStartIconType {

    data class IconButtonRowCell(
        @DrawableRes val iconRes: Int,
        val iconTint: Color,
        val onClick: () -> Unit
    ) : RowCellStartIconType()

    data class IconRowCell(
        @DrawableRes val iconRes: Int,
        val iconTint: Color
    ) : RowCellStartIconType()
}
