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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.elbehiry.delish.R
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionRequired
import com.google.accompanist.permissions.rememberPermissionState

@ExperimentalPermissionsApi
@Composable
fun MapView(
    mapViewModel: MapViewModel,
    onBackClicked: () -> Unit
) {
    val locationPermission = rememberPermissionState(
        android.Manifest.permission.ACCESS_FINE_LOCATION
    )
    PermissionRequired(
        permissionState = locationPermission,
        permissionNotGrantedContent = {
            LocationNoGranted {
                locationPermission.launchPermissionRequest()
            }
        },
        permissionNotAvailableContent = {}
    ) {
        mapViewModel.getRestaurantByCurrentLocation()
        MapViewContent(mapViewModel) {
            onBackClicked()
        }
    }
}

@Composable
fun LocationNoGranted(
    onButtonClicked: () -> Unit
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (title, Button) = createRefs()
        Text(
            text = stringResource(id = R.string.location_needed),
            style = MaterialTheme.typography.h6,
            textAlign = TextAlign.Center,
            color = Color.White,
            modifier = Modifier
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .constrainAs(title) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                        top = parent.top,
                        bottom = parent.bottom
                    )
                }
        )

        Button(
            onClick = { onButtonClicked() },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 32.dp)
                .constrainAs(Button) {
                    linkTo(
                        start = parent.start,
                        end = parent.end,
                    )
                    top.linkTo(title.bottom)
                }
        ) {
            Text(text = stringResource(id = R.string.request_permission))
        }
    }
}

@Preview
@Composable
fun PreviewLocationNoGranted() {
    LocationNoGranted {}
}
