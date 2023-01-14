package com.jaehong.presenter.ui.studytype

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.theme.Gray1
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.ui.MainActivity.Companion.studyList
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun StudyTypeScreen(
    studyTypeViewModel: StudyTypeViewModel = hiltViewModel()
) {
    val dynastyType = studyTypeViewModel.dynastyState.collectAsState().value
    val isVisible = studyTypeViewModel.isVisible.collectAsState().value
    val markImage = painterResource(id = R.drawable.woo_su_mark)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray1),
        contentAlignment = Alignment.Center,
    ) {

        val animationHeight by animateIntAsState(
            targetValue = if(isVisible) 400 else 50,
            animationSpec = tween(
                delayMillis = 100
            )
        )
        val animationRadius by animateIntAsState(
            targetValue = if(isVisible) 10 else 50,
            animationSpec = tween(
                delayMillis = 100
            )
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(animationHeight.dp)
                .background(Color.White, RoundedCornerShape(animationRadius)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            studyList.forEach {
                TextButton(onClick = {
                    studyTypeViewModel.onNavigateToStudyPageClicked(dynastyType,it)
                }) {
                    Text(
                        text = it,
                        fontSize = 50.nonScaledSp,
                        modifier = Modifier.padding(15.dp),
                        style = Typography.bodyMedium,
                        color = Color.Black,
                    )
                }
            }
        }

        Box( modifier = Modifier
            .offset(y = (-(animationHeight/2)).dp)
            .background(BaseColor1, CircleShape)
            .width(250.dp)
            .height(40.dp),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = dynastyType,
                textAlign = TextAlign.Center,
                fontSize = 35.nonScaledSp,
                color = Color.White,
                )
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

    studyTypeViewModel.startAnimation()
}