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

package com.elbehiry.delish.ui.map

import androidx.annotation.DrawableRes
import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.elbehiry.delish.R

/**
 * A variant of the map UI. Depending on the variant, Map UI may show different markers, tile
 * overlays, etc.
 */
enum class MapVariant(
    @StringRes val labelResId: Int,
    @DrawableRes val iconResId: Int,
    @RawRes val styleResId: Int,
) {
    Standard(
        R.string.map_variant_standard,
        R.drawable.ic_standard_map,
        R.raw.map_style_default
    ),
    Dark(
        R.string.map_variant_after_dark,
        R.drawable.ic_map_after_dark,
        R.raw.map_style_night
    ),
    DAY(
        R.string.map_variant_daytime,
        R.drawable.ic_map_daytime,
        R.raw.map_style_day
    );
}
