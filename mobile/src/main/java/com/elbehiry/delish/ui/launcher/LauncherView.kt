package com.elbehiry.delish.ui.launcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.vectorResource
import com.elbehiry.delish.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LauncherView(
    viewModel: LauncherViewModel,
    modifier: Modifier = Modifier,
    onLauncherComplete: (Boolean) -> Unit
) {
    val launchDestination: Boolean by viewModel.launchDestination.observeAsState(false)
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onLauncherComplete)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout(launchDestination)
        }

        Image(
            imageVector = vectorResource(id = R.drawable.ic_recipe)
        )
    }
}