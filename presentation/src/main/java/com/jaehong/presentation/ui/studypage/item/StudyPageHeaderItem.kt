package com.jaehong.presentation.ui.studypage.item

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.theme.BaseColor1
import com.jaehong.presentation.util.Extension.checkedType
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun StudyPageHeaderItem(
    dynastyState:String,
    pageTitle: String,
    header: String = if(dynastyState.checkedType()) pageTitle else "$dynastyState, $pageTitle"
){
    Box(modifier = Modifier
        .fillMaxWidth()
        .background(BaseColor1, RoundedCornerShape(50, 50, 0, 0)),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = header,
            textAlign = TextAlign.Center,
            fontSize = 30.nonScaledSp,
            color = Color.White,
            lineHeight = 30.nonScaledSp,
            modifier = Modifier
                .padding(vertical = 7.dp)
        )
    }
}