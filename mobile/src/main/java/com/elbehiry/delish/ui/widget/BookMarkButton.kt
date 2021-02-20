package com.elbehiry.delish.ui.widget

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.preferredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkBorder
import androidx.compose.material.icons.outlined.Bookmark
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.elbehiry.delish.R

@Composable
fun BookMarkButton(
    modifier: Modifier = Modifier,
    selected: Boolean = false
) {
    val icon = if (selected) Icons.Outlined.Bookmark else Icons.Filled.BookmarkBorder
    Surface(
        color = colorResource(id = R.color.black_alpha),
        shape = CircleShape,
        modifier = modifier.preferredSize(36.dp, 36.dp)
    ) {
        Icon(
            imageVector = icon,
            tint = colorResource(id = android.R.color.white),
            contentDescription = null,
            modifier = Modifier.clickable {}
        )
    }
}

@Composable
@Preview
fun previewBookMarkButtonClicked(){
    BookMarkButton(selected = false)
}

@Composable
@Preview
fun previewBookMarkButtonUnClicked(){
    BookMarkButton(selected = true)
}