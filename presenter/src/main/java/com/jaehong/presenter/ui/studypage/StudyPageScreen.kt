package com.jaehong.presenter.ui.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.DynastyButtonColor

@Composable
fun StudyPageScreen(
    studyTypeViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState = studyTypeViewModel.dynastyState.collectAsState().value
    val studyState = studyTypeViewModel.studyState.collectAsState().value
    val studyData = studyTypeViewModel.studyInfoList.collectAsState().value

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.DarkGray
    ) {
        LazyColumn(
            modifier = Modifier.padding(30.dp),
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
            itemsIndexed(studyData) {index, studyInfo ->
                ItemView(studyInfo)
            }
        }
    }
}

@Composable
fun ItemView(
    studyInfo: StudyInfoItem,
){
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.DarkGray else Color.White
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .clickable(
                onClick = {selected = !selected}
            )
            .background(backgroundColor)
        ,

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
            textAlign = TextAlign.Center,
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

