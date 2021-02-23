package com.elbehiry.delish.ui.main

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.preferredHeight
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.bookmark.BookMark
import com.elbehiry.delish.ui.home.HomeContent
import com.elbehiry.delish.ui.plan.MealPlan
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@Composable
fun MainContent(
    viewModel: MainViewModel,
    onIngredientContent: () -> Unit
) {

    val (selectedTab, setSelectedTab) = remember { mutableStateOf(DelishHomeTabs.Home) }

    val tabs = DelishHomeTabs.values()
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
    ) { innerPadding ->
        val modifier = Modifier.padding(innerPadding)
        when (selectedTab) {
            DelishHomeTabs.Home -> HomeContent(viewModel,onIngredientContent,modifier)
            DelishHomeTabs.BookMark -> BookMark(modifier)
            DelishHomeTabs.MealPlan -> MealPlan(modifier)
        }
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
                .align(Alignment.CenterVertically)
        )

    }
}

enum class DelishHomeTabs(
    @StringRes val title: Int,
    @DrawableRes val icon: Int
) {
    Home(R.string.recipes_tab, R.drawable.ic_chef),
    BookMark(R.string.book_mark, R.drawable.ic_bookmark),
    MealPlan(R.string.meal_plan, R.drawable.ic_plan)
}