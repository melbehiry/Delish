package com.elbehiry.delish.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = yellow200,
    secondary = blue200,
    onSecondary = Color.Black,
    surface = yellowDarkPrimary,
    background = background,
    onBackground = background800,
    primaryVariant = purple500,
    onPrimary = Color.White,
)

private val LightColorPalette = lightColors(
    background = Color.White,
    onBackground = Color.White,
    surface = Color.White,
    primary = purple200,
    primaryVariant = purple500,
    secondary = purple500,
    onPrimary = Color.White,
    onSecondary = Color.White
)

@Composable
fun DelishComposeTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    val typography = if (darkTheme) {
        DarkTypography
    } else {
        LightTypography
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}