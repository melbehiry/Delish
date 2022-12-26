package app.delish.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.delish.compose.ui.AppBarIcon
import kotlinx.coroutines.launch
import kotlinx.coroutines.CoroutineScope

@Composable
fun DetailsTopBar(
    contentPadding: PaddingValues = PaddingValues(16.dp),
    scrollToTop: suspend CoroutineScope.() -> Unit,
    firstVisibleItemIndex: () -> Int,
    onOptionsClicked: () -> Unit
) {

    val visible by remember { derivedStateOf { firstVisibleItemIndex() > 0 } }
    AnimatedVisibility(
        visible = visible,
        enter = slideInVertically(initialOffsetY = { -it }),
        exit = slideOutVertically(targetOffsetY = { -it })
    ) {
        Column(
            Modifier
                .background(color = MaterialTheme.colors.background)
                .fillMaxWidth()
        ) {
            Row (
                Modifier
                    .background(color = MaterialTheme.colors.background)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                val composableScope = rememberCoroutineScope()
                AppBarIcon(
                    Icons.Filled.KeyboardArrowUp,
                    contentDescription = stringResource(R.string.go_to_details),
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(
                            start = contentPadding.calculateStartPadding(LayoutDirection.Rtl),
                            top = contentPadding.calculateTopPadding(),
                            bottom = contentPadding.calculateBottomPadding(),
                        ),
                    onClicked = {
                        composableScope.launch(block = scrollToTop)
                    }
                )
                Text(
                    text = "Details",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(
                        top = contentPadding.calculateTopPadding(),
                        bottom = contentPadding.calculateBottomPadding()
                    )
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
            Spacer(
                modifier = Modifier
                    .height(1.dp)
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.background)
            )
        }

    }
}
