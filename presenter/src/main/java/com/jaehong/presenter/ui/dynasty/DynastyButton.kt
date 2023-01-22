package com.jaehong.presenter.ui.dynasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import com.jaehong.presenter.ui.MainActivity

@Composable
fun DynastyButton(
    isVisible: Boolean,
    markImage: Painter,
    logoImage: @Composable (Painter) -> Unit,
    dynastyButtonItem: @Composable (String,Boolean) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MainActivity.dynastyList.forEach {
                dynastyButtonItem(it,isVisible)
            }
        }
        logoImage(markImage)
    }
}