package com.jaehong.koreanhistory.presenter.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.koreanhistory.ui.theme.DynastyButtonColor

@Composable
fun StudyPageScreen(
    studyTypeViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState = studyTypeViewModel.dynastyState.collectAsState().value
    val studyState = studyTypeViewModel.studyState.collectAsState().value
    val data = studyTypeViewModel.studyInfoList.collectAsState().value
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        LazyColumn(
            modifier = Modifier.padding(30.dp)
        ) {
            item {
                Text(
                    text = dynastyState,
                    modifier = Modifier
                        .background(DynastyButtonColor, RoundedCornerShape(50, 50, 0, 0))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color.White
                )
            }
            items(data) { studyInfo ->
                Row(
                    modifier = Modifier.height(IntrinsicSize.Max).background(Color.White),
                ) {
                    Text(
                        text = studyInfo.title,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .border(1.dp, Color.Black, RectangleShape)
                            .weight(0.5f)
                            .fillMaxHeight()
                            .background(Color.LightGray)
                            .wrapContentSize(Alignment.Center),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        text = studyInfo.description,
                        fontSize = 25.sp,
                        modifier = Modifier
                            .border(1.dp, Color.Black, RectangleShape)
                            .weight(1f)
                            .fillMaxHeight()
                            .padding(5.dp)
                        ,
                    )
                }
            }
        }
    }
}