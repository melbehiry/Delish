package com.elbehiry.delish.ui.launcher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.elbehiry.delish.ui.main.launchMainActivity
import com.elbehiry.delish.ui.onboarding.launchOnBoardingActivity
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.MAIN_ACTIVITY
import com.elbehiry.delish.ui.launcher.LauncherViewModel.LaunchDestination.ONBOARDING
import com.elbehiry.delish.ui.util.checkAllMatched

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelishComposeTheme {
                LauncherView(viewModel = launcherViewModel,
                    onLauncherComplete = { destination ->
                        when (destination) {
                            MAIN_ACTIVITY -> launchMainActivity(context = this)
                            ONBOARDING ->  launchOnBoardingActivity(context = this)
                        }.checkAllMatched
                        finish()
                    })
            }
        }
    }
}


