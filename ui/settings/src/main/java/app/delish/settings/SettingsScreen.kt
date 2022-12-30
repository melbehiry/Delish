package app.delish.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import app.delish.settings.models.Settings
import app.delish.compose.view.rowCell.RowCell
import app.delish.compose.view.rowCell.RowCellStartIconType
import app.delish.compose.view.rowCell.RowCellTextType

@Composable
fun SettingsScreen(
    navController: NavController,
    bottomBarPadding: PaddingValues
) {
    SettingsScreen(
        viewModel = hiltViewModel(),
        navController = navController,
        bottomBarPadding = bottomBarPadding
    )
}

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel,
    navController: NavController,
    bottomBarPadding: PaddingValues
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(bottomBarPadding)
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(start = 16.dp, end = 16.dp)
        ) {
            item {
                Text(
                    text = stringResource(id = R.string.settings_title),
                    fontSize = 34.sp,
                    modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
                    color = Color.White
                )
            }

            val settings = viewModel.getSettings()
            settings.forEachIndexed { index, list ->
                item {
                    GroupSettingsItem(list)
                }

                if (index.plus(1) != settings.size) {
                    item {
                        Spacer(modifier = Modifier.height(24.dp))
                    }
                }
            }

            item {
                Spacer(modifier = Modifier.height(24.dp))
            }
        }
    }
}


@Composable
private fun GroupSettingsItem(settings: List<Settings>) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        backgroundColor = Color.DarkGray,
        shape = RoundedCornerShape(14.dp),
        elevation = 0.dp
    ) {
        Column(modifier = Modifier.fillMaxWidth()) {
            settings.forEachIndexed { index, item ->
                RowCell(
                    RowCellTextType.OnlyLabel(
                        label = stringResource(id = item.name),
                        value = item.value?.let {
                            stringResource(id = item.value)
                        }
                    ),
                    rowCellStartIconType = RowCellStartIconType.IconRowCell(
                        iconRes = item.icon,
                        iconTint = item.getIconTint()
                    ),
                    onClick = {
                        settingsItemPressed(item = item)
                    },
                    endIcon = item.withEndIcon(),
                    withDivider = index.plus(1) != settings.size,
                    dividerColor = MaterialTheme.colors.onSurface.copy(alpha = 0.08f)
                )
            }
        }
    }
}

@Composable
private fun Settings.getIconTint() = when (this) {
    Settings.ShareApp -> MaterialTheme.colors.primary
    else -> Color(0x99EBEBF5)
}

@Composable
private fun Settings.withEndIcon() = when (this) {
    Settings.ShareApp, Settings.AskQuestion -> null
    else -> painterResource(id = R.drawable.ic_row_cell_arrow_end)
}

private fun settingsItemPressed(
    item: Settings
) {
    when (item) {
        is Settings.Language -> {}
        Settings.ShareApp -> {}
        Settings.AskQuestion -> {}
    }
}
