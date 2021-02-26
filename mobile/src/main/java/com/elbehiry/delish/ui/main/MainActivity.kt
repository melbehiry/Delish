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

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.CompositionLocalProvider
import com.elbehiry.delish.ui.graph.NavGraph
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import com.elbehiry.delish.ui.util.LocalBackDispatcher
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

fun launchMainActivity(context: Context) {
    context.startActivity(Intent(context, MainActivity::class.java))
}

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainViewModel.getRandomRecipes()

        setContent {
            DelishComposeTheme {
                CompositionLocalProvider(LocalBackDispatcher provides onBackPressedDispatcher) {
                    ProvideWindowInsets {
                        NavGraph(mainViewModel)
                    }
                }
            }
        }
    }
}
