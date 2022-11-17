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

package com.delish.compose.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout

@Composable
fun EmptyView(
    modifier: Modifier = Modifier,
    imageResourceId : Int,
    titleText: String? = "",
    descText: String? = ""
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (image, title, desc) = createRefs()
        Image(
            painter = painterResource(id = imageResourceId),
            contentDescription = "empty",
            modifier = Modifier.constrainAs(image) {
                linkTo(
                    start = parent.start,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom
                )
            }
        )
        Text(
            text = titleText ?: "",
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 16.dp).constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(image.bottom)
                }
        )
        Text(
            text = descText ?: "",
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp).constrainAs(desc) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(title.bottom)
                }
        )
    }
}
