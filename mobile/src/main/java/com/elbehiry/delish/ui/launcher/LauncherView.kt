package com.elbehiry.delish.ui.launcher

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.imageResource
import com.elbehiry.delish.R
import kotlinx.coroutines.delay

private const val SplashWaitTime: Long = 2000

@Composable
fun LauncherView(
    viewModel: LauncherViewModel,
    modifier: Modifier = Modifier,
    onLauncherComplete: (LauncherViewModel.LaunchDestination) -> Unit
) {
    val launchDestination: LauncherViewModel.LaunchDestination by
    viewModel.launchDestination.observeAsState(LauncherViewModel.LaunchDestination.ONBOARDING)
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.grey)),
        contentAlignment = Alignment.Center
    ) {
        val currentOnTimeout by rememberUpdatedState(onLauncherComplete)
        LaunchedEffect(Unit) {
            delay(SplashWaitTime)
            currentOnTimeout(launchDestination)
        }

        Image(
            bitmap = imageResource(id = R.drawable.logo),
            contentDescription = null
        )
    }
}