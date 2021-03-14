package com.elbehiry.delish.ui.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.elbehiry.delish.R

@ExperimentalAnimationApi
@Composable
fun SearchAppBar(
    title:String,
    onTextChanged: (TextFieldValue) -> Unit,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    searchHint : String,
    hintColor : Color = Color(0xFF8B8C8F),
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
            }) {
            Surface(
                modifier = Modifier.align(Alignment.CenterEnd).padding(end= 8.dp),
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
                                keyboardActions= KeyboardActions(onSearch= {
                                    onSearch()
                                }),
                                maxLines = 1,
                                singleLine = true,
                                cursorBrush = SolidColor(Color.White),
                                textStyle = MaterialTheme.typography.body1.copy(color = Color.White),
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
                                    style = MaterialTheme.typography.body1.copy(color= hintColor)
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
