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

package com.delish.compose.theme

import androidx.compose.material.Colors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

val purple200 = Color(0xFF651FFF)
val purple500 = Color(0xFF6200EA)
val background = Color(0xFF2B292B)
val background800 = Color(0xFF424242)
val background900 = Color(0xFF212121)
val white87 = Color(0Xddffffff)

val darkPrimary = Color(0xff242316)

val blue200 = Color(0xff91a4fc)
val Green500 = Color(0xFF1EB980)
val DarkBlue900 = Color(0xFF26282F)
val orangeError = Color(0xFFF94701)

@Composable
fun Colors.compositedOnSurface(alpha: Float): Color {
    return onSurface.copy(alpha = alpha).compositeOver(surface)
}

@Composable
fun Colors.randomBackgroundColor(): Color {
    val colors: List<Color> = listOf(
        Color(0xFFD0EFB3),
        Color(0xFFC0D6E3),
        Color(0xFFD8D8D8),
        Color(0xFFEE7B7E),
        Color(0xFFFFD48F),
        Color(0xFFD8D8D8)
    )

    return colors.random()
}
