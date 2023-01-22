package com.jaehong.presenter.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp

@Composable
fun LogoImage(
    markImage: Painter
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = markImage,
            contentDescription = "Signature Mark",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(100.dp)
                .padding(bottom = 30.dp)
        )
    }
}