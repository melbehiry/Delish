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

package app.delish.compose.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.IconButton
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension

@Composable
@Suppress("LongParameterList")
fun SearchAppBar(
    title: String,
    onTextChanged: (TextFieldValue) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    searchHint: String,
    hintColor: Color = Color(0xFF8B8C8F),
    keyboardType: KeyboardType = KeyboardType.Text,
    textFieldValue: TextFieldValue,
    backgroundColor: Color = Color(0xFF1F1F1F),
    onBackPressed: () -> Unit,
    onSearch: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    ConstraintLayout(
        modifier = Modifier.fillMaxWidth()
            .background(MaterialTheme.colors.onBackground)
            .height(56.dp)
    ) {
        val (back, content, searchContent, searchText, search) = createRefs()
        IconButton(
            onClick = {
                if (expanded) {
                    expanded = !expanded
                    onTextChanged(TextFieldValue(""))
                } else {
                    onBackPressed()
                }
            },
            Modifier.constrainAs(back) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top, margin = 8.dp)
            }
        ) {
            Icon(
                imageVector = if (!expanded) Icons.Filled.ArrowBack else Icons.Filled.Close,
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }

        Box(
            modifier = Modifier.constrainAs(content) {
                linkTo(
                    start = back.end,
                    end = parent.end,
                    top = parent.top,
                    bottom = parent.bottom
                )
                width = Dimension.fillToConstraints
            }
        ) {
            Surface(
                modifier = Modifier.align(Alignment.CenterEnd).padding(end = 8.dp),
                shape = MaterialTheme.shapes.small.copy(CornerSize(percent = 50)),
                color = backgroundColor
            ) {
                Box(
                    modifier = Modifier
                        .defaultMinSize(minWidth = 40.dp, minHeight = 40.dp)
                        .indication(interactionSource, rememberRipple()),
                    contentAlignment = Alignment.Center
                ) {
                    ConstraintLayout {
                        AnimatedVisibility(visible = expanded) {
                            BasicTextField(
                                value = textFieldValue,
                                onValueChange = { onTextChanged(it) },
                                keyboardOptions = KeyboardOptions(
                                    keyboardType = keyboardType,
                                    imeAction = ImeAction.Search
                                ),
                                keyboardActions =
                                KeyboardActions(
                                    onSearch = {
                                        onSearch()
                                    }
                                ),
                                maxLines = 1,
                                singleLine = true,
                                cursorBrush = SolidColor(Color.White),
                                textStyle =
                                MaterialTheme.typography.body1.copy(color = Color.White),
                                modifier = Modifier.padding(horizontal = 16.dp)
                                    .fillMaxWidth()
                                    .constrainAs(searchContent) {
                                        start.linkTo(back.end, margin = 8.dp)
                                        end.linkTo(search.start, margin = 8.dp)
                                        width = Dimension.fillToConstraints
                                    }
                            )
                            if (textFieldValue.text.isEmpty()) {
                                Text(
                                    modifier = Modifier.padding(horizontal = 16.dp)
                                        .constrainAs(searchText) {
                                            start.linkTo(back.end, margin = 8.dp)
                                            end.linkTo(search.start, margin = 8.dp)
                                            width = Dimension.fillToConstraints
                                        },
                                    text = searchHint,
                                    style = MaterialTheme.typography.body1.copy(color = hintColor)
                                )
                            }
                        }

                        AnimatedVisibility(visible = !expanded) {
                            IconButton(
                                modifier = Modifier.size(24.dp).constrainAs(search) {
                                    start.linkTo(searchContent.end, margin = 8.dp)
                                    end.linkTo(parent.end, margin = 8.dp)
                                },
                                onClick = {
                                    expanded = !expanded
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Filled.Search,
                                    contentDescription = "",
                                    tint = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
