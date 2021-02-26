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

    private val mainViewModel : MainViewModel by viewModels()

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