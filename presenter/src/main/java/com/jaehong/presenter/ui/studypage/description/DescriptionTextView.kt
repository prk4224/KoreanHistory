package com.jaehong.presenter.ui.studypage.description

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.GenericShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DescriptionTextView(
    description: String,
    originDescription: String,
    studyState: String,
    selectedItem: StudyInfoItem,
    selected: Boolean,
    setSelected: (Boolean) -> Unit,
    backgroundColor: Color = if (selected) Gray2 else Color.White,
    alphaText: Float = if(selected || studyState != StudyType.ALL_BLANK_REVIEW.value) 1f else 0f,
    hintText: String = if (selected) originDescription else description,
    changeSelectedItem: (StudyInfoItem,Boolean) -> Unit,
    changeButtonState: () -> Unit,
    changeAllHintState: () -> Unit,
) {
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
            .combinedClickable(
                onClick = {
                    setSelected(selected.not())
                    changeSelectedItem(selectedItem,selected.not())
                    if (studyState == StudyType.ORIGIN_STUDY.value) {
                        changeButtonState()
                    }
                },
                onLongClick = {
                    if (studyState == StudyType.FIRST_REVIEW.value) {
                        changeAllHintState()
                    }
                }
            ),
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