package app.delish.view.rowCell

sealed class RowCellTextType {

    data class OnlyLabel(
        val label: String,
        val value: String? = null
    ) : RowCellTextType()

    data class WithCaption(
        val label: String,
        val caption: String,
        val value: String? = null
    ) : RowCellTextType()

    data class WithTitle(
        val label: String,
        val title: String,
        val value: String? = null
    ) : RowCellTextType()
}
