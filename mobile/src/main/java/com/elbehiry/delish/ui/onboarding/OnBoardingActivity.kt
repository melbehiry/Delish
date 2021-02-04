package com.elbehiry.delish.ui.onboarding

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.ui.platform.setContent
import com.elbehiry.delish.ui.theme.DelishComposeTheme
import dagger.hilt.android.AndroidEntryPoint
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets

fun launchOnBoardingActivity(context: Context) {
    context.startActivity(Intent(context, OnBoardingActivity::class.java))
}

@AndroidEntryPoint
class OnBoardingActivity : ComponentActivity() {

    private val onBoardingViewModel: OnBoardingViewModel by viewModels()

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ProvideWindowInsets {
                DelishComposeTheme {
                    OnBoardingContent(onBoardingViewModel){
                        finish()
                    }
                }
            }
        }
    }
}