package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.MyStudyColor
import com.jaehong.presenter.util.Constants
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun MyStudyHeaderItem(title: String) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .height(60.dp)
            .background(MyStudyColor, RoundedCornerShape(50, 50, 0, 0))
    ){
        Text(
            text = Constants.MY_KEYWORD,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 35.nonScaledSp,
            color = Color.White
        )
        Text(
            text =title,
            modifier = Modifier
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontSize = 24.nonScaledSp,
            color = Color.White
        )
    }
}