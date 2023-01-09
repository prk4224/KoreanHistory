package com.jaehong.presenter.ui.dynasty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.theme.DynastyButtonColor
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.ui.MainActivity
import com.jaehong.presenter.util.Constants.MY_KEYWORD

@Composable
fun DynastyScreen(
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    val isVisible = dynastyViewModel.isVisible.collectAsState().value

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .background(Color.White),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MainActivity.dynastyList.forEach {
                DynastyButton(title = it, isVisible)
            }
        }
    }
    dynastyViewModel.startAnimation()
}

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
                if (title == MY_KEYWORD) {
                    dynastyViewModel.onNavigateToMyStudyClicked()
                } else {
                    dynastyViewModel.onNavigateToStudyTypeClicked(title)
                }
            },
            Modifier
                .width(250.dp)
                .background(Color.White)
                .padding(15.dp),
            colors = ButtonDefaults.buttonColors(DynastyButtonColor)
        ) {
            Text(text = title, style = Typography.bodyMedium)
        }
    }

}
