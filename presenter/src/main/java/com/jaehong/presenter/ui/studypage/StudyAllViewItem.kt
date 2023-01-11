package com.jaehong.presenter.ui.studypage

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
import androidx.compose.ui.unit.sp
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

@Composable
fun StudyAllViewItem(
    studyInfo: StudyInfoItem,
    index: Int,
    allStudyData: StudyInfo,
    studyState: String,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(Color.White),
    ) {
        Text(
            text = studyInfo.king_name,
            fontSize = 25.sp,
            modifier = Modifier
                .border(1.dp, Color.Black, RectangleShape)
                .weight(0.5f)
                .fillMaxHeight()
                .background(Color.LightGray)
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
        )

        Column(modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.Black, RectangleShape)
            .fillMaxHeight()
            ,) {
            studyInfo.description.forEachIndexed { descIndex, description ->
                DescriptionTextView(
                    studyInfo,
                    description,
                    index,
                    descIndex,
                    allStudyData,
                    studyState
                )
            }
        }
    }
}