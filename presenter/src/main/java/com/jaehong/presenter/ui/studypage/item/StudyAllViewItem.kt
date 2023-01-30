package com.jaehong.presenter.ui.studypage.item

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
import com.jaehong.presenter.util.Constants.NOTHING
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun StudyAllViewItem(
    studyInfo: StudyInfoItem,
    descriptionView: @Composable (Int,String) -> Unit
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
        Column(modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.LightGray, RectangleShape)
            .fillMaxHeight(),
        ) {
            studyInfo.description.forEachIndexed { descIndex, description ->
                descriptionView(descIndex,description)
            }
        }
    }
}