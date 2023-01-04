package com.jaehong.presenter.ui.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
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

val selectedItems = mutableStateMapOf<Int, Boolean>()

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
                ItemView(studyInfo,index)
            }
        }
    }
}

@Composable
fun ItemView(studyInfo: StudyInfoItem, index: Int){
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .background(ItemClicked(index))
            .clickable(
                onClick = {
                    selectedItems[index] != selectedItems[index]
                }
            )
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

@Composable
fun ItemClicked(index: Int): Color{
    return if(selectedItems[index] == null ){
        selectedItems[index] = false
        Color.White
    } else if(selectedItems[index] == false) {
        Color.White
    } else {
        Color.DarkGray
    }
}

