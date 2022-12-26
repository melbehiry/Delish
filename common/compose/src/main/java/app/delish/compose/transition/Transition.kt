/*
 * Copyright 2022 Alexandre Gomes Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package app.delish.compose.transition

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.Layout
import com.google.accompanist.pager.PagerDefaults
import com.google.accompanist.pager.PagerState
import dev.chrisbanes.snapper.ExperimentalSnapperApi
import kotlin.math.absoluteValue
import androidx.compose.ui.util.lerp

@Composable
fun <Data> AlphaTransition(
    dataList: List<Data>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    enableGesture: () -> Boolean = { true },
    content: @Composable (data: Data) -> Unit
) = Transition(dataList, pagerState, modifier, enableGesture) { data, fraction, isTarget ->
    Box(
        Modifier.graphicsLayer {
            alpha = lerp(
                start = if (isTarget) 0f else 1f,
                stop = if (isTarget) 1f else 0f,
                fraction = fraction()
            )
        }
    ) {
        content(data)
    }
}

@Composable
fun <Data> HorizontalSlideTransition(
    dataList: List<Data>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    enableGesture: () -> Boolean = { true },
    content: @Composable (data: Data) -> Unit
) = Transition(dataList, pagerState, modifier, enableGesture) { data, fraction, isTarget ->
    Layout(
        content = {
            content(data)
        }
    ) { measurables, constraints ->
        val currentPlaceable = measurables.first().measure(constraints)

        val width = currentPlaceable.width
        val height = currentPlaceable.height

        val value = lerp(
            start = if (isTarget) {
                width
            } else 0,
            stop = if (isTarget.not()) {
                -width
            } else 0,
            fraction = fraction()
        )

        layout(width, height) {
            currentPlaceable.placeRelative(x = value, y = 0)
        }
    }
}

@Composable
fun <Data> Transition(
    dataList: List<Data>,
    pagerState: PagerState,
    modifier: Modifier = Modifier,
    enableGesture: () -> Boolean = { true },
    content: @Composable (data: Data, fraction: () -> Float, isTarget: Boolean) -> Unit
) {
    val transitionData = getTransitionData(dataList, getPageOffset = { pagerState.getPageOffset() })

    val boxModifier = if (enableGesture()) {
        modifier.transitionHorizontalScrollable(pagerState)
    } else modifier
    Box(boxModifier) {
        content(
            transitionData.data,
            { transitionData.fraction },
            isTarget = false
        )
        if (transitionData.data != transitionData.nextData) {
            content(
                transitionData.nextData,
                { transitionData.fraction },
                isTarget = true
            )
        }
    }
}

@OptIn(ExperimentalSnapperApi::class)
fun Modifier.transitionHorizontalScrollable(
    pagerState: PagerState
): Modifier = composed {
    scrollable(
        orientation = Orientation.Horizontal,
        reverseDirection = true,
        flingBehavior = PagerDefaults.flingBehavior(pagerState),
        state = pagerState
    )
}

fun <Data> getTransitionData(
    dataList: List<Data>,
    getPageOffset: () -> Float
): TransitionData<Data> {
    val pageOffset = getPageOffset()
    val currentPageOffsetDecimal = pageOffset - pageOffset.toInt()
    val currentIndex = pageOffset.toInt()
    val nextIndex = if (currentPageOffsetDecimal > 0f) {
        pageOffset.toInt() + 1
    } else pageOffset.toInt()

    val fraction = currentPageOffsetDecimal.absoluteValue.coerceIn(0f, 1f)
    return TransitionData(dataList[currentIndex], dataList[nextIndex], fraction)
}

fun PagerState.getPageOffset(): Float {
    return (this.currentPage + this.currentPageOffset).coerceAtLeast(0f)
}

data class TransitionData<Data>(
    val data: Data,
    val nextData: Data,
    val fraction: Float
)
