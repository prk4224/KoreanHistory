package com.jaehong.presenter.ui.dynasty

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.ui.MainActivity
import com.jaehong.presenter.util.Constants.MY_KEYWORD
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun DynastyScreen(
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    val isVisible = dynastyViewModel.isVisible.collectAsState().value
    val markImage = painterResource(id = R.drawable.woo_su_mark)


    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.Center),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            MainActivity.dynastyList.forEach {
                DynastyButton(title = it, isVisible)
            }
        }

        Image(
            painter = markImage,
            contentDescription = "Signature Mark",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .size(100.dp)
                .padding(bottom = 30.dp)
        )
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
                .width(300.dp)
                .background(Color.White)
                .padding(12.dp),
            colors = ButtonDefaults.buttonColors(BaseColor1)
        ) {
            Text(text = title, style = Typography.bodyMedium, fontSize = 35.nonScaledSp)
        }
    }
}
