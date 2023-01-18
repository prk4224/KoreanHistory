package com.jaehong.presenter.ui.dynasty

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
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.util.Constants
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun DynastyButton(
    title: String,
    isVisible: Boolean,
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    AnimatedVisibility(
        visible = isVisible,
        enter = slideInHorizontally(initialOffsetX = {
            -it
        }),
        exit = slideOutHorizontally(targetOffsetX = {
            -it
        })
    ) {
        Button(
            onClick = {
                if (title == Constants.MY_KEYWORD) {
                    dynastyViewModel.onNavigateToMyStudyClicked()
                } else {
                    dynastyViewModel.onNavigateToStudyTypeClicked(title)
                }
            },
            Modifier
                .width(300.dp)
                .background(Color.White)
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(BaseColor1)
        ) {
            Text(text = title, style = Typography.bodyMedium, fontSize = 35.nonScaledSp)
        }
    }
}