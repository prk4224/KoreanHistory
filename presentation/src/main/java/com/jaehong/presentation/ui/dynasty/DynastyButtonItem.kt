package com.jaehong.presentation.ui.dynasty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.theme.BaseColor1
import com.jaehong.presentation.theme.Typography
import com.jaehong.presentation.util.FontFixed.nonScaledSp
import com.jaehong.domain.local.model.enum_type.DynastyType

@Composable
fun DynastyButtonItem(
    title: DynastyType,
    visible: Boolean,
    toMyStudyClicked: () -> Unit,
    toStudyTypeClicked: () -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        enter = slideInHorizontally(initialOffsetX = {
            -it
        }),
        exit = slideOutHorizontally(targetOffsetX = {
            -it
        })
    ) {
        Button(
            onClick = {
                if (title == DynastyType.MY_KEYWORD) {
                    toMyStudyClicked()
                } else {
                    toStudyTypeClicked()
                }
            },
            Modifier
                .width(300.dp)
                .background(Color.White)
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(BaseColor1)
        ) {
            Text(
                text = title.value,
                style = Typography.bodyMedium,
                fontSize = 35.nonScaledSp,
                color = Color.White
            )
        }
    }
}