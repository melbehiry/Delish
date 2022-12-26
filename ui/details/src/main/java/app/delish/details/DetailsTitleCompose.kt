package app.delish.details

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import app.delish.details.R
import app.delish.compose.ui.AppBarIcon


@Composable
fun DetailsTitleCompose(
    title: String,
    subtitle: String,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(16.dp),
    onOptionsClicked: () -> Unit = {}
) = Row(
    modifier
        .fillMaxWidth()
) {
    ScreenHeader(
        title = title,
        subTitle = subtitle,
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                end = 8.dp
            ),
        contentPadding = contentPadding
    )

    OptionIcon(
        Modifier
            .align(Alignment.CenterVertically)
            .padding(
                end = contentPadding.calculateEndPadding(LayoutDirection.Rtl),
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding()
            ),
        onOptionsClicked
    )
}

@Composable
fun OptionIcon(
    modifier: Modifier,
    onOptionsClicked: (() -> Unit)? = null
) {
    AppBarIcon(
        Icons.Filled.MoreVert,
        contentDescription = stringResource(R.string.recipe_detail_options),
        modifier = modifier,
        onClicked = onOptionsClicked
    )
}

@Preview
@Composable
private fun MonsterTitleWithSubtitlePreview() {
    DetailsTitleCompose(
        "Title",
        "SubTitle"
    ){
    }
}
