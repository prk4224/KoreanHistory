package com.jaehong.presenter.ui.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun DescriptionTextView(
    studyInfo: StudyInfoItem,
    description: String,
    studyIndex: Int,
    descriptionIndex: Int,
    allStudyData: StudyInfo,
    studyState: String,
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val selectedItem = StudyInfoItem(
            studyInfo.id + descriptionIndex,
            studyInfo.detail,
            studyInfo.king_name,
            arrayListOf(description)
    )
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Gray2 else Color.White
    val alphaText = if(selected || studyState != ALL_BLANK_REVIEW) 1f else 0f
    val hintText = if (selected) allStudyData[studyIndex].description[descriptionIndex] else description

    Text(
        fontSize = 25.nonScaledSp,
        lineHeight = 25.nonScaledSp,
        text = hintText,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.LightGray, getTopLineShape())
            .padding(5.dp)
            .background(backgroundColor)
            .alpha(alphaText)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        selected = selected.not()
                        studyPageViewModel.changeSelectedItem(selectedItem, selected)
                        if (studyState == ORIGIN_STUDY) {
                            studyPageViewModel.changeButtonState()
                        }
                    },
                    onLongPress = {
                        if (studyState == FIRST_REVIEW) {
                            studyPageViewModel.changeAllHintState()
                        }
                    }
                )
            },
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