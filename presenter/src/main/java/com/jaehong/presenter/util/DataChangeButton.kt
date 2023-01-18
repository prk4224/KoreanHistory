package com.jaehong.presenter.util

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.BaseColor1


@Composable
fun DataChangeButton(
    iconType: Boolean,
    isVisible: Boolean,
    onIconClicked: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()){
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(10.dp),
            enter = slideInHorizontally(initialOffsetX = {
                +it
            }),
            exit = slideOutHorizontally(targetOffsetX = {
                +it
            }),
        ) {
            IconButton(
                onClick = onIconClicked,
            ) {
                Icon(
                    imageVector = if(iconType) Icons.Filled.AddCircle else Icons.Filled.RemoveCircle,
                    tint = BaseColor1,
                    contentDescription = null,
                    modifier = Modifier
                        .size(50.dp)
                        .background(Color.White)
                )
            }
        }
    }

}