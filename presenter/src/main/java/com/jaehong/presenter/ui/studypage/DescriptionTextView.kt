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
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.util.Constants

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
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.LightGray else Color.White
    val alphaText = if(selected || studyState != Constants.ALL_BLANK_REVIEW) 1f else 0f
    val hintText = if (selected) allStudyData[studyIndex].description[descriptionIndex] else description

    Text(
        text = hintText,
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, getTopLineShape())
            .padding(5.dp)
            .background(backgroundColor)
            .alpha(alphaText)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        when (studyState) {
                            Constants.ORIGIN_STUDY -> {
                                selected = selected.not()
                                with(studyPageViewModel) {
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
                            }
                            else -> {
                                selected = selected.not()
                            }
                        }
                    },
                    onLongPress = {
                        studyPageViewModel.changeAllHintState()
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