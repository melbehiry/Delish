package app.delish.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ScreenHeader(
    title: String,
    modifier: Modifier = Modifier,
    subTitle: String? = null,
    contentPadding: PaddingValues = PaddingValues(0.dp),
    titleFontSize: HeaderFontSize = HeaderFontSize.LARGE,
) = Column(modifier) {
    val titleFontSizeValue = when (titleFontSize) {
        HeaderFontSize.LARGE -> 24.sp
        HeaderFontSize.SMALL -> 16.sp
    }
    val titleFontWeight = when (titleFontSize) {
        HeaderFontSize.LARGE -> FontWeight.Bold
        HeaderFontSize.SMALL -> FontWeight.SemiBold
    }
    val titleBottomPadding = if (subTitle != null) 0.dp else {
        contentPadding.calculateBottomPadding()
    }
    Text(
        text = title,
        fontSize = titleFontSizeValue,
        maxLines = 2,
        modifier = Modifier.padding(
            top = contentPadding.calculateTopPadding(),
            bottom = titleBottomPadding,
            start = contentPadding.calculateStartPadding(LayoutDirection.Rtl)
        )
    )
    subTitle?.let {
        Text(
            text = it,
            fontSize = 12.sp,
            fontWeight = FontWeight.Light,
            fontStyle = FontStyle.Normal,
            maxLines = 1,
            modifier = Modifier.padding(
                bottom = contentPadding.calculateBottomPadding(),
                start = contentPadding.calculateStartPadding(LayoutDirection.Rtl)
            )
        )
    }
}

enum class HeaderFontSize {
    LARGE, SMALL
}
