package com.elbehiry.delish.ui.util

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.runtime.staticAmbientOf

/**
 * An [androidx.compose.runtime.Ambient] providing the current [OnBackPressedDispatcher]. You must
 * [provide][androidx.compose.runtime.Providers] a value before use.
 */
internal val AmbientBackDispatcher = staticAmbientOf<OnBackPressedDispatcher> {
    error("No Back Dispatcher provided")
}
