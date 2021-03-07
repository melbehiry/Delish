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

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@ExperimentalAnimationApi
@Composable
fun NotificationBanner(
    isOpen: Boolean,
    notificationMessage: String,
    backgroundColor: Color,
    textColor: Color,
    buttonText: String,
    buttonTextColor: Color,
    onRetryClicked: () -> Unit
) {
    AnimatedVisibility(visible = isOpen) {
        Row(
            Modifier.background(backgroundColor)
                .fillMaxWidth()
                .wrapContentHeight()
        ) {
            Text(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp).weight(3f),
                text = notificationMessage,
                color = textColor,
                style = MaterialTheme.typography.subtitle2,
                maxLines = 2
            )

            TextButton(
                modifier = Modifier.align(Alignment.CenterVertically).padding(8.dp).weight(1f),
                onClick = { onRetryClicked() }
            ) {
                Text(
                    text = buttonText,
                    color = buttonTextColor,
                    style = MaterialTheme.typography.subtitle2
                )
            }
        }
    }
}
