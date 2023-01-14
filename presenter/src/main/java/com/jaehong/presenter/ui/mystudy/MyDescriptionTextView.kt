package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
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
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Gray2 else Color.White

    Text(
        fontSize = 25.nonScaledSp,
        lineHeight = 25.nonScaledSp,
        text = description,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, RectangleShape)
            .padding(5.dp)
            .background(backgroundColor)
            .clickable(
                onClick = {
                    with(myStudyViewModel) {
                        selected = selected.not()

                        val selectedItem = StudyInfoItem(
                            studyInfo.id + descriptionIndex,
                            studyInfo.detail,
                            studyInfo.king_name,
                            arrayListOf(description)
                        )

                        if (selected) {
                            addSelectedItem(selectedItem)
                        } else {
                            removeSelectedItem(selectedItem)
                        }
                        if (selectedItems.value.size > 0) {
                            changeButtonState(true)
                        } else {
                            changeButtonState(false)
                        }
                    }
                },
            )
    )
}