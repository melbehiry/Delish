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

package com.elbehiry.delish.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import com.elbehiry.delish.ui.home.graph.HomeNavGraph
import com.elbehiry.delish.ui.search.SearchType

@AndroidEntryPoint
class HomeFragment : Fragment() {

    @ExperimentalFoundationApi
    @ExperimentalAnimationApi
    @SuppressLint("VisibleForTests")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                DelishComposeTheme {
                    ProvideWindowInsets {
                        HomeNavGraph(
                            onCuisineSearch = { cuisine ->
                                val action = HomeFragmentDirections
                                    .goToSearchScreen(cuisine, SearchType.CUISINE)
                                findNavController().navigate(action)
                            },
                            onIngredientSearch = { query ->
                                val action =
                                    HomeFragmentDirections.goToSearchScreen(query, SearchType.QUERY)
                                findNavController().navigate(action)
                            }
                        ) { recipeId ->
                            val action = HomeFragmentDirections.goToRecipesDetails(recipeId)
                            findNavController().navigate(action)
                        }
                    }
                }
            }
        }
    }
}
