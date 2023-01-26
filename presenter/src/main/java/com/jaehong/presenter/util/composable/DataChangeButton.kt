package com.jaehong.presenter.util.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.BaseColor1


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataChangeButton(
    iconType: Boolean,
    isVisible: Boolean,
    onIconClicked: () -> Unit,
    onIconLongClicked: () ->Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            enter = slideInHorizontally(initialOffsetX = {
                +it
            }),
            exit = slideOutHorizontally(targetOffsetX = {
                +it
            }),
        ) {
            Icon(imageVector = if (iconType) Icons.Filled.AddCircle else Icons.Filled.RemoveCircle,
                tint = BaseColor1,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .background(Color.White, CircleShape)
                    .combinedClickable(
                        onClick = { onIconClicked() },
                        onLongClick = { onIconLongClicked() }
                    )
            )
        }
    }
}