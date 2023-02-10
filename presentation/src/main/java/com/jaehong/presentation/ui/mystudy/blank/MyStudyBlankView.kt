package com.jaehong.presentation.ui.mystudy.blank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.R
import com.jaehong.presentation.theme.Gray3
import com.jaehong.presentation.theme.Typography
import com.jaehong.presentation.util.Constants.PLUS_KEYWORD_TEXT
import com.jaehong.presentation.util.Constants.REVIEW_TEXT
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun MyStudyBlankView(
    blankImage: Painter = painterResource(id = R.drawable.black_image),
    onBackButtonClicked: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray3),
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = blankImage,
                contentDescription = null,
                modifier = Modifier.size(230.dp)
            )

            Text(
                text = REVIEW_TEXT,
                fontSize = 26.nonScaledSp,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = Color.Black,
                modifier = Modifier.padding(10.dp),
            )

            Button(
                onClick = { onBackButtonClicked() },
                colors = ButtonDefaults.buttonColors(Color.White)
            ) {
                Text(
                    text = PLUS_KEYWORD_TEXT,
                    style = Typography.bodyLarge,
                    color = Color.Black,
                    fontSize = 22.nonScaledSp
                )
            }
        }
    }
}