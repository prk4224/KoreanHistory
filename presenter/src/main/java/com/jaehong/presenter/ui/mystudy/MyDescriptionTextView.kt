package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun MyDescriptionTextView(
    studyInfo: StudyInfoItem,
    description: String,
    descriptionIndex: Int,
    myStudyViewModel: MyStudyViewModel = hiltViewModel(),
) {
    val selectedItem = StudyInfoItem(
        studyInfo.id + descriptionIndex,
        studyInfo.detail,
        studyInfo.king_name,
        arrayListOf(description)
    )

    var selected by remember(selectedItem.id) { mutableStateOf(false) }

    val backgroundColor = if(selected) Gray2 else Color.White

    Text(
        fontSize = 25.nonScaledSp,
        lineHeight = 25.nonScaledSp,
        text = description,
        color = Color.Black,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, getTopLineShape())
            .padding(5.dp)
            .background(backgroundColor)
            .clickable(
                onClick = {
                    selected = selected.not()
                    with(myStudyViewModel) {
                        changeSelectedItem(selectedItem, selected)
                        changeButtonState()
                    }
                },
            )
    )
}

private fun getTopLineShape() : Shape {
    return GenericShape { size, _ ->
        moveTo(0f, 0f )
        lineTo(size.width, 0f )
        lineTo(size.width, 0f - 2f)
        lineTo(0f, 0f - 2f )
    }
}