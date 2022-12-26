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

package app.delish.view

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.LocalContentColor
import androidx.compose.material.ContentAlpha
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import app.delish.common.ui.R

@Composable
fun ToggleAddButton(
    isAdded: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    var select by remember { mutableStateOf(isAdded) }

    IconButton(
        onClick = {
            select = !select
            onClick()
        },
        modifier = modifier
    ) {
        Icon(
            imageVector = when {
                select -> Icons.Default.Check
                else -> Icons.Default.Add
            },
            contentDescription = when {
                select -> stringResource(R.string.remove)
                else -> stringResource(R.string.add)
            },
            tint = animateColorAsState(
                when {
                    select -> LocalContentColor.current
                    else -> Color.Black.copy(alpha = ContentAlpha.high)
                }
            ).value,
            modifier = Modifier
                .shadow(
                    elevation = 0.dp,
                    shape = MaterialTheme.shapes.small
                )
                .background(
                    color = animateColorAsState(
                        when {
                            select -> MaterialTheme.colors.surface.copy(0.38f)
                            else -> Color.White
                        }
                    ).value,
                    shape = RoundedCornerShape(percent = 50)
                )
                .padding(4.dp)
        )
    }
}

@Composable
@Preview
fun PreviewToggleAddButton() {
    ToggleAddButton(false) {}
}

@Composable
@Preview
fun PreviewToggleAddedButton() {
    ToggleAddButton(true) {}
}
