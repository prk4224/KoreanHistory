package com.jaehong.koreanhistory.presenter.studytype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.koreanhistory.presenter.MainActivity.Companion.studyList
import com.jaehong.koreanhistory.ui.theme.DynastyButtonColor
import com.jaehong.koreanhistory.ui.theme.Typography

@Composable
fun StudyTypeScreen(
    studyTypeViewModel: StudyTypeViewModel = hiltViewModel()
) {
    val dynastyType = studyTypeViewModel.dynastyState.collectAsState().value
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.LightGray),
        contentAlignment = Alignment.Center,
    ) {

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp)
                .height(400.dp)
                .background(Color.White, RoundedCornerShape(10)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            studyList.forEach {
                TextButton(onClick = {
                    studyTypeViewModel.onNavigateToStudyPageClicked(dynastyType,it)
                }) {
                    Text(
                        text = it,
                        fontSize = 40.sp,
                        modifier = Modifier.padding(15.dp),
                        style = Typography.bodyLarge,
                        color = Color.Black,
                    )
                }
            }
        }
        Text(
            text = dynastyType,
            textAlign = TextAlign.Center,
            fontSize = 35.sp,
            color = Color.White,
            modifier = Modifier
                .offset(y = (-200).dp)
                .background(DynastyButtonColor, CircleShape)
                .width(250.dp)

        )
    }
}