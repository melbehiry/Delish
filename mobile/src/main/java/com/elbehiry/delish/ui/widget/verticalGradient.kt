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

package com.elbehiry.delish.ui.widget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

fun Modifier.verticalGradient(): Modifier = composed {
    val color = Color.Black.copy(alpha = 0.3f)
    val colors = remember { listOf(color.copy(alpha = 0f), color) }

    var height by remember { mutableStateOf(0f) }
    val brush = remember(color, height) {
        Brush.verticalGradient(
            colors = colors,
            startY = height * 1f,
            endY = height * 0f
        )
    }

    drawBehind {
        height = size.height
        drawRect(brush = brush)
    }
}
