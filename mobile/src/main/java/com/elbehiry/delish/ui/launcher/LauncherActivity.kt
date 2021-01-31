package com.elbehiry.delish.ui.launcher

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.elbehiry.delish.ui.main.launchMainActivity
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LauncherActivity : ComponentActivity() {

    private val launcherViewModel: LauncherViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DelishComposeTheme {
                LauncherView(viewModel = launcherViewModel,
                    onLauncherComplete = { onBoardingCompleted ->
                        launchMainActivity(
                            context = this,
                            onBoardingCompleted = onBoardingCompleted
                        )
                        finish()
                    })
            }
        }
    }
}


