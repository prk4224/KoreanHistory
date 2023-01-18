package com.jaehong.presenter.ui.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
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
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun StudyPageHeaderItem(
    dynastyState:String,
    pageTitle: String
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .height(40.dp)
        .background(BaseColor1, RoundedCornerShape(50, 50, 0, 0)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "$dynastyState, $pageTitle",
            textAlign = TextAlign.Center,
            fontSize = 30.nonScaledSp,
            color = Color.White
        )
    }
}