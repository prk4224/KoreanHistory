package com.jaehong.presentation.ui.studytype

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.theme.Typography
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun StudyTypeButtonItem(
    dynastyType: String,
    studyType: String,
    toStudyPageClicked: (String,String) -> Unit
) {
    TextButton(onClick = {
        toStudyPageClicked(dynastyType,studyType)
    }) {
        Text(
            text = studyType,
            fontSize = 50.nonScaledSp,
            modifier = Modifier.padding(15.dp),
            style = Typography.bodyMedium,
            color = Color.Black,
        )
    }
}