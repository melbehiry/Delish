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

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import java.time.DayOfWeek

const val WEIGHT_7DAY_WEEK = 1 / 7f

@Composable
fun DaysHeader(
    selectedIndex: Int = 0,
    selectedTextColor: Color = Color.White,
    selectedBackgroundColor: Color = Color.White,
    onClick: (Int) -> Unit
) {
    val daysOfWeek = DayOfWeek.values().toSet()
    var select by remember { mutableStateOf(selectedIndex) }

    Row(
        modifier = Modifier.fillMaxWidth()
            .padding(vertical = 8.dp),
    ) {
        daysOfWeek.forEachIndexed { index, dayOfWeek ->
            DefaultDay(
                text = dayOfWeek.name.first().toString(),
                selected = select == index,
                backgroundColor = selectedBackgroundColor,
                textColor = selectedTextColor,
                modifier = Modifier.weight(WEIGHT_7DAY_WEEK).alpha(.6f)
            ) {
                select = index
                onClick(index)
            }
        }
    }
}

@Composable
fun DefaultDay(
    text: String,
    selected: Boolean,
    backgroundColor: Color,
    textColor: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(CircleShape)
            .requiredSize(40.dp)
            .padding(4.dp)
            .clickable { onClick() }
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            drawCircle(
                color = if (selected) backgroundColor else Color.White.copy(.8f),
                style = Stroke(1.dp.toPx())
            )
        }
        Text(
            text = text,
            color = if (selected) textColor else Color.White,
            style = MaterialTheme.typography.subtitle2,
            modifier = Modifier.align(Alignment.Center).padding(4.dp)
        )
    }
}
