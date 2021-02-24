package com.elbehiry.delish.ui.theme

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

val yellow200 = Color(0xffffeb46)
val yellowDarkPrimary = Color(0xff242316)

val blue200 = Color(0xff91a4fc)

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
