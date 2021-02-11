package com.elbehiry.delish.ui.home

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.ConstraintLayout
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elbehiry.delish.R
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun HomeContent(
    selectedTab: DelishHomeTabs,
    setSelectedTab: (DelishHomeTabs) -> Unit
) {

    val tabs = DelishHomeTabs.values()

    ConstraintLayout {
        Scaffold(
            backgroundColor = MaterialTheme.colors.primarySurface,
            topBar = { HomeTopBar() },
            bottomBar = {
                BottomNavigation {
                    tabs.forEach { tab ->
                        BottomNavigationItem(
                            icon = {
                                Icon(
                                    imageVector = vectorResource(id = tab.icon),
                                    contentDescription = null
                                )
                            },
                            label = { Text(text = stringResource(id = tab.title)) },
                            selected = tab == selectedTab,
                            onClick = { setSelectedTab(tab) },
                            alwaysShowLabels = false,
                            selectedContentColor = AmbientContentColor.current,
                            unselectedContentColor = AmbientContentColor.current,
                            modifier = Modifier.navigationBarsPadding()
                        )
                    }
                }
            }
        ) {}
//        { innerPadding ->
//            val modifier = Modifier.padding(innerPadding)
//            when (selectedTab) {
//                StellerHomeTab.HOME -> HomePhotos(photos, selectPhoto, modifier)
//                else -> HomePhotos(photos, selectPhoto, modifier)
//            }
//        }
//        CircularProgressIndicator(
//            modifier = Modifier.constrainAs(progress) {
//                top.linkTo(parent.top)
//                bottom.linkTo(parent.bottom)
//                start.linkTo(parent.start)
//                end.linkTo(parent.end)
//            }.visible(isLoading)
//        )
//    }
    }
}

@Composable
fun HomeTopBar() {
    TopAppBar(
        elevation = 6.dp,
        modifier = Modifier.preferredHeight(58.dp)
    ) {

        Text(
            text = stringResource(id = R.string.app_name),
            color = Color.White,
            fontSize = 18.sp,
            fontWeight = FontWeight.Black,
            modifier = Modifier
                .padding(8.dp)
        )

    }
}

enum class DelishHomeTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    Home(R.string.home_tab, R.drawable.ic_grain),
    Featured(R.string.feature_tab, R.drawable.ic_featured)
}