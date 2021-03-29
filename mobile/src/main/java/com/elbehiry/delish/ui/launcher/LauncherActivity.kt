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

package com.elbehiry.delish.ui.launcher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.elbehiry.delish.ui.main.launchMainActivity
import com.elbehiry.delish.ui.onboarding.launchOnBoardingActivity
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.elbehiry.delish.ui.launcher.LaunchDestination.MAIN_ACTIVITY
import com.elbehiry.delish.ui.launcher.LaunchDestination.ON_BOARDING
import com.elbehiry.delish.ui.util.checkAllMatched

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelishComposeTheme {
                LauncherView(
                    onLauncherComplete = { destination ->
                        when (destination) {
                            MAIN_ACTIVITY -> launchMainActivity(context = this)
                            ON_BOARDING -> launchOnBoardingActivity(context = this)
                        }.checkAllMatched
                        finish()
                    }
                )
            }
        }
    }
}
