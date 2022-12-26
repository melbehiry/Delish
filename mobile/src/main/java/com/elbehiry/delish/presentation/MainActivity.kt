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

package com.elbehiry.delish.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.ViewTreeViewModelStoreOwner
import androidx.savedstate.findViewTreeSavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import app.delish.compose.theme.BottomSheetShape
import app.delish.compose.theme.DelishComposeTheme
import com.elbehiry.delish.presentation.navigation.AppNavigation
import com.elbehiry.delish.presentation.navigation.BottomBar
import com.elbehiry.delish.presentation.navigation.Navigate
import com.elbehiry.delish.presentation.vm.MainViewModel
import com.elbehiry.delish.presentation.vm.ViewEvent
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.navigation.material.ModalBottomSheetLayout
import com.google.accompanist.navigation.material.rememberBottomSheetNavigator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()
    private var keepSplashScreen = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        WindowCompat.setDecorFitsSystemWindows(window, false)

        installSplashScreen().setKeepOnScreenCondition {
            keepSplashScreen
        }

        setContent {
            DelishComposeTheme {
                MainScreen(mainViewModel= mainViewModel){
                    keepSplashScreen = false
                }
            }
        }
        setOwners()
    }
}

private fun ComponentActivity.setOwners() {
    val decorView = window.decorView
    if (ViewTreeLifecycleOwner.get(decorView) == null) {
        ViewTreeLifecycleOwner.set(decorView, this)
    }
    if (ViewTreeViewModelStoreOwner.get(decorView) == null) {
        ViewTreeViewModelStoreOwner.set(decorView, this)
    }
    if (decorView.findViewTreeSavedStateRegistryOwner() == null) {
        decorView.setViewTreeSavedStateRegistryOwner(this)
    }
}

