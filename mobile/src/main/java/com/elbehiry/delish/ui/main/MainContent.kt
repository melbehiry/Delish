/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.elbehiry.delish.ui.main

import androidx.annotation.StringRes
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.LocalContentColor
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BlurOn
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.filled.EventNote
import androidx.compose.material.primarySurface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.bookmark.BookMark
import com.elbehiry.delish.ui.home.HomeContent
import com.elbehiry.delish.ui.plan.MealPlan
import dev.chrisbanes.accompanist.insets.navigationBarsPadding

@ExperimentalFoundationApi
@ExperimentalAnimationApi
@Composable
fun MainContent(
    viewModel: MainViewModel,
    onIngredientContent: () -> Unit,
    onCuisineSearch: (String) -> Unit,
    onDetails: (Int) -> Unit,
    onIngredientSearch: (String) -> Unit
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
                                imageVector = tab.icon,
                                contentDescription = null
                            )
                        },
                        label = { Text(text = stringResource(id = tab.title)) },
                        selected = tab == selectedTab,
                        onClick = { setSelectedTab(tab) },
                        alwaysShowLabel = false,
                        selectedContentColor = MaterialTheme.colors.secondary,
                        unselectedContentColor = LocalContentColor.current,
                        modifier = Modifier.navigationBarsPadding()
                    )
                }
            }
        }
    ) {
        when (selectedTab) {
            DelishHomeTabs.Home -> HomeContent(
                viewModel,
                onIngredientContent,
                onCuisineSearch,
                onDetails,
                onIngredientSearch
            )
            DelishHomeTabs.BookMark -> BookMark(viewModel, onDetails)
            DelishHomeTabs.MealPlan -> MealPlan()
        }
    }
}

@Composable
fun HomeTopBar() {
    TopAppBar(
        elevation = 6.dp
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
    val icon: ImageVector
) {
    Home(R.string.recipes_tab, Icons.Filled.BlurOn),
    BookMark(R.string.book_mark, Icons.Filled.BookmarkBorder),
    MealPlan(R.string.meal_plan, Icons.Filled.EventNote)
}
