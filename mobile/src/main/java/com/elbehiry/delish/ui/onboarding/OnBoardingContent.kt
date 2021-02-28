package com.elbehiry.delish.ui.onboarding

import androidx.annotation.VisibleForTesting
import androidx.compose.animation.*
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Album
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import com.elbehiry.delish.R
import com.elbehiry.delish.ui.main.launchMainActivity
import dev.chrisbanes.accompanist.coil.CoilImage
import dev.chrisbanes.accompanist.insets.statusBarsPadding
import kotlinx.coroutines.NonCancellable.start
import java.time.temporal.TemporalAdjusters.next

@ExperimentalAnimationApi
@VisibleForTesting
@Composable
fun OnBoardingContent(
    viewModel: OnBoardingViewModel,
    onBoardingFinished: () -> Unit
) {
    val onBoardingItemsList = viewModel.getOnBoardingItemsList()
    val launchDestination: Boolean by viewModel.navigateToMainActivity.observeAsState(false)

    if (launchDestination) {
        launchMainActivity(context = LocalContext.current)
        onBoardingFinished()
    }

    val currentPage = remember { mutableStateOf(0) }
    val transition = updateTransition(targetState = currentPage)
    val rotation by transition.animateFloat({
        tween(durationMillis = 1000)
    }) {
        if (it.value == 0) 0f else it.value * 360f
    }

    val getStartedVisible = remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.background(Color.White).fillMaxSize()
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth().align(Alignment.BottomCenter),
            color = Color.White
        ) {
            AnimatedVisibility(
                visible = getStartedVisible.value,
                enter = slideInVertically(initialOffsetY = { -40 }) + expandVertically(
                    expandFrom = Alignment.Top
                ) + fadeIn(initialAlpha = 0.3f)
            ) {
                Button(
                    onClick = {
                        viewModel.getStartedClick()
                    },
                    modifier = Modifier
                        .padding(vertical = 32.dp, horizontal = 120.dp),
                    shape = MaterialTheme.shapes.small
                ) {
                    Text(
                        text = stringResource(id = R.string.onBoarding_start),
                        style = MaterialTheme.typography.subtitle2,
                        color = Color.White
                    )
                }
            }
            AnimatedVisibility(
                visible = !getStartedVisible.value,
                exit = slideOutVertically() + shrinkVertically() + fadeOut()
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceBetween, modifier =
                    Modifier.fillMaxWidth().padding(8.dp)
                ) {
                    IconButton(
                        modifier = Modifier.align(Alignment.CenterVertically),
                        onClick = {
                        viewModel.getStartedClick()
                        }
                    ) {
                        Text(
                            text = stringResource(id = R.string.skip),
                            color = MaterialTheme.colors.background
                        )
                    }
                    Row(
                        modifier = Modifier.align(Alignment.CenterVertically)
                    ) {
                        onBoardingItemsList.forEachIndexed { index, _ ->
                            OnBoardingSlide(
                                selected = index == currentPage.value,
                                MaterialTheme.colors.primary,
                                Icons.Filled.Album
                            )
                        }
                    }
                    TextButton(onClick = {
                        if (currentPage.value != onBoardingItemsList.size - 1) {
                            currentPage.value = currentPage.value + 1
                        }
                        if (currentPage.value != onBoardingItemsList.size - 2) {
                            getStartedVisible.value = true
                        }
                    }, modifier = Modifier.align(Alignment.CenterVertically)) {
                        Text(
                            text = stringResource(id = R.string.next),
                            color = MaterialTheme.colors.background
                        )
                        Image(
                            imageVector = Icons.Filled.KeyboardArrowRight,
                            contentDescription = null
                        )
                    }
                }
            }
        }


        Surface(
            color = MaterialTheme.colors.background,
            modifier = Modifier.size(550.dp).fillMaxWidth(),
            shape = RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .statusBarsPadding()
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Image(
                        painter = painterResource(R.drawable.ic_delish_logo),
                        modifier = Modifier.padding(16.dp).width(80.dp),
                        contentDescription = null
                    )
                }
                Spacer(modifier = Modifier.height(60.dp))
                Box(contentAlignment = Alignment.Center) {
                    Image(
                        painter = painterResource(id = R.drawable.empty_plate),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(250.dp, 250.dp)
                            .rotate(rotation)
                    )

                    Image(
                        painter = painterResource(onBoardingItemsList[currentPage.value].contentImageId),
                        contentDescription = null,
                        modifier = Modifier
                            .requiredSize(130.dp, 130.dp)

                    )
                }

                Text(
                    text = stringResource(id = onBoardingItemsList[currentPage.value].titleId),
                    style = MaterialTheme.typography.h5,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    modifier = Modifier.padding(
                        start = 30.dp, top = 30.dp, end = 30.dp
                    )
                )
                Text(
                    text = stringResource(id = onBoardingItemsList[currentPage.value].DescriptionId),
                    style = MaterialTheme.typography.body2,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(start = 60.dp, top = 8.dp, end = 60.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun OnBoardingSlide(selected: Boolean, color: Color, icon: ImageVector) {
    Icon(
        imageVector = icon,
        modifier = Modifier.padding(4.dp).size(12.dp),
        contentDescription = null,
        tint = if (selected) color else Color.Gray
    )
}
