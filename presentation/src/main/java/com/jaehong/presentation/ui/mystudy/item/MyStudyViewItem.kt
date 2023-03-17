package com.jaehong.presentation.ui.mystudy.item

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presentation.util.Constants.NOTHING
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun MyStudyViewItem(
    studyInfo: StudyInfoItem,
    myDescription: @Composable (StudyInfoItem,String) -> Unit
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(Color.White),
    ) {
        if(studyInfo.king_name != NOTHING) {
            Text(
                text = studyInfo.king_name,
                fontSize = 25.nonScaledSp,
                lineHeight = 25.nonScaledSp,
                color = Color.Black,
                modifier = Modifier
                    .border(1.dp, Color.LightGray, RectangleShape)
                    .weight(0.5f)
                    .fillMaxHeight()
                    .wrapContentSize(Alignment.Center),
                textAlign = TextAlign.Center,
            )
        }

        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            studyInfo.description.forEach { description ->
                myDescription(studyInfo,description)
            }
        }
    }
}