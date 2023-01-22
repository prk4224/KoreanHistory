package com.jaehong.presenter.ui.studypage.description

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
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.FontFixed.nonScaledSp
import com.jaehong.presenter.util.enum.StudyType

@Composable
fun DescriptionTextView(
    studyInfo: StudyInfoItem,
    description: String,
    descriptionIndex: Int,
    originDescription: String,
    studyState: String,
    changeSelectedItem: (StudyInfoItem,Boolean) -> Unit,
    changeButtonState: () -> Unit,
    changeAllHintState: () -> Unit,
) {
    val selectedItem = StudyInfoItem(
            studyInfo.id + descriptionIndex,
            studyInfo.detail,
            studyInfo.king_name,
            arrayListOf(description)
    )
    var selected by remember(studyInfo.description[descriptionIndex]) { mutableStateOf(false) }

    val backgroundColor = if (selected) Gray2 else Color.White
    val alphaText = if(selected || studyState != StudyType.ALL_BLANK_REVIEW.value) 1f else 0f
    val hintText = if (selected) originDescription else description

    Text(
        fontSize = 25.nonScaledSp,
        lineHeight = 25.nonScaledSp,
        text = hintText,
        color = Color.Black,
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
                        changeSelectedItem(selectedItem,selected)
                        if (studyState == StudyType.ORIGIN_STUDY.value) {
                            changeButtonState()
                        }
                    },
                    onLongPress = {
                        if (studyState == StudyType.FIRST_REVIEW.value) {
                            changeAllHintState()
                        }
                    }
                )
            },
        )
}

@Composable
fun TextField(
    selected: Boolean,
    changeSelected: (Boolean) -> Unit
) {

}

private fun getTopLineShape() : Shape {
    return GenericShape { size, _ ->
        moveTo(0f, 0f )
        lineTo(size.width, 0f )
        lineTo(size.width, 0f - 2f)
        lineTo(0f, 0f - 2f )
    }
}