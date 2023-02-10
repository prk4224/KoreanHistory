package com.jaehong.presenter.ui.mystudy.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.Constants
import com.jaehong.presenter.util.FontFixed.nonScaledSp


@Composable
fun MyStudyNoticeItem() {
    Text(
        text = Constants.MY_STUDY_TEXT,
        fontSize = 24.nonScaledSp,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RectangleShape)
            .background(Gray2)
            .padding(8.dp)

    )
}