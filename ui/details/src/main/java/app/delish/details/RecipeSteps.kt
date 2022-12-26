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

package app.delish.details

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import app.delish.details.R
import app.delish.view.RecipesStepsSelection

@Composable
fun RecipeSteps(steps: List<String>?) {
    if (steps?.size != 0) {
        val selectionIndex = remember { mutableStateOf(0) }
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(id = R.string.method),
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 4.dp),
                color = Color.White
            )

            RecipeStepItem(steps?.get(selectionIndex.value) ?: "")

            RecipesStepsSelection(
                stepsCount = steps?.size ?: 0,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                selectionIndex.value = it
            }
        }
    }
}

@Composable
fun RecipeStepItem(summary: String?) {
    var extended by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth().wrapContentHeight().padding(16.dp)
    ) {
        val (summery, extend) = createRefs()
        Text(
            text = summary ?: "",
            color = Color.White,
            style = MaterialTheme.typography.caption,
            textAlign = TextAlign.Start,
            overflow = TextOverflow.Ellipsis,
            maxLines = 10,
            modifier = Modifier.padding(start = 16.dp, end = 8.dp)
                .fillMaxWidth()
                .constrainAs(summery) {
                    linkTo(
                        start = parent.start,
                        end = extend.start,
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )
        Icon(
            imageVector = if (extended) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
            contentDescription = null,
            tint = Color.White,
            modifier = Modifier.padding(end = 16.dp)
                .clickable {
                    extended = !extended
                }.constrainAs(extend) {
                    linkTo(
                        start = summery.end,
                        end = parent.end
                    )
                }
        )
    }
}
