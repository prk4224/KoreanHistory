package com.jaehong.presenter.ui.modern_after

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun TypeCheckButtonItem(
    dynastyType: String,
    detailType: String,
    toStudyTypeClicked: (String,String) -> Unit
) {
    TextButton(onClick = {
        toStudyTypeClicked(dynastyType,detailType)
    }) {
        Text(
            text = detailType,
            fontSize = 38.nonScaledSp,
            modifier = Modifier.padding(15.dp),
            style = Typography.bodyMedium,
            color = Color.Black,
        )
    }
}