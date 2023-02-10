package com.jaehong.presentation.ui.modern_after

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
fun TypeCheckButtonItem(
    dynastyType: String,
    detailType: String,
    toStudyTypeClicked: (String,String) -> Unit
) {
    TextButton(
        onClick = { toStudyTypeClicked(dynastyType,detailType) },
        modifier = Modifier.padding(15.dp),
    ) {
        Text(
            text = detailType,
            fontSize = 38.nonScaledSp,
            lineHeight = 38.nonScaledSp,
            style = Typography.bodyMedium,
            color = Color.Black,
        )
    }
}