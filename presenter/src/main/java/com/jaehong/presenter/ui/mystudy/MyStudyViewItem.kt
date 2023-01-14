package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun MyStudyViewItem(
    studyInfo: StudyInfoItem,
) {
    val selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Gray2 else Color.White

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .background(backgroundColor),
    ) {
        Text(
            text = studyInfo.king_name,
            fontSize = 25.nonScaledSp,
            lineHeight = 25.nonScaledSp,
            modifier = Modifier
                .border(1.dp, Color.LightGray, RectangleShape)
                .weight(0.5f)
                .fillMaxHeight()
                .wrapContentSize(Alignment.Center),
            textAlign = TextAlign.Center,
        )
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            studyInfo.description.forEachIndexed { index, description ->
                MyDescriptionTextView(studyInfo, description, index)
            }
        }
    }
}