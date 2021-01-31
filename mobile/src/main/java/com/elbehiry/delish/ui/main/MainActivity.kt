package com.elbehiry.delish.ui.main

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.annotation.VisibleForTesting
import androidx.compose.ui.platform.setContent
import com.elbehiry.delish.ui.MainContent
import com.elbehiry.delish.ui.theme.DelishComposeTheme

private const val KEY_ARG_ON_BOARDING_NAME = "KEY_ARG_ON_BOARDING_NAME"

fun launchMainActivity(context: Context, onBoardingCompleted: Boolean) {
    context.startActivity(createMainActivityIntent(context, onBoardingCompleted))
}

@VisibleForTesting
fun createMainActivityIntent(context: Context, onBoardingCompleted: Boolean): Intent {
    return Intent(context, MainActivity::class.java).apply {
        putExtra(KEY_ARG_ON_BOARDING_NAME, onBoardingCompleted)
    }
}

class MainActivity : ComponentActivity() {

    @SuppressLint("VisibleForTests")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DelishComposeTheme {
                MainContent(
                    isOnBoardingCompleted =
                    intent.getBooleanExtra(KEY_ARG_ON_BOARDING_NAME, false),
                    backDispatcher = onBackPressedDispatcher
                )
            }
        }
    }
}