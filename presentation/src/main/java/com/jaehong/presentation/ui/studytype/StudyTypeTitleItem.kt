package com.jaehong.presentation.ui.studytype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
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
fun StudyTypeTitleItem(
    dynastyType: String,
    animationHeight: Int,
) {
    Box(
        modifier = Modifier
            .offset(y = (-(animationHeight / 2)).dp)
            .background(BaseColor1, CircleShape)
            .width(250.dp),
        contentAlignment = Alignment.Center

    ) {
        Text(
            text = getHeaderText(dynastyType),
            textAlign = TextAlign.Center,
            fontSize = 35.nonScaledSp,
            color = Color.White,
            lineHeight = 35.nonScaledSp,
            modifier = Modifier.padding(vertical = 7.dp)
        )
    }
}


private fun getHeaderText(dynastyType: String): String {
    val index = dynastyType.indexOf("\n")
    return if(dynastyType.checkedType()) {
        dynastyType.substring(index+1)
    }
    else dynastyType
}
