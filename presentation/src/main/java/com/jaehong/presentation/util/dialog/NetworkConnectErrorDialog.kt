package com.jaehong.presentation.util.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.jaehong.presentation.theme.BlackWithAlpha50
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun NetworkConnectErrorDialog() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackWithAlpha50)
    ) {
        Text(
            modifier = Modifier
                .align(Alignment.Center),
            textAlign = TextAlign.Center,
            text = "Network를 연결해주세요.",
            color = Color.Red,
            fontSize = 30.nonScaledSp,
        )
    }
}