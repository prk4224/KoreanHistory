package com.jaehong.presenter.ui.studypage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.DynastyButtonColor
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY

@Composable
fun StudyPageScreen(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState = studyPageViewModel.dynastyState.collectAsState().value
    val studyState = studyPageViewModel.studyState.collectAsState().value
    val studyData = studyPageViewModel.studyInfoList.collectAsState().value
    val isVisible = studyPageViewModel.isVisible.collectAsState().value
    val allStudyData = studyPageViewModel.allStudyInfoList.collectAsState().value
    val selectedItems = studyPageViewModel.selectedItems.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        LazyColumn(
            modifier = Modifier.padding(30.dp),
        ) {
            val data = when (studyState) {
                ORIGIN_STUDY -> allStudyData
                else -> studyData
            }

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
            itemsIndexed(data) { index, studyInfo ->
                StudyAllViewItem(studyInfo, index, allStudyData, studyState)
            }
        }

        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier.align(Alignment.BottomEnd),
            enter = slideInHorizontally(initialOffsetX = {
                +it
            }),
            exit = slideOutHorizontally(targetOffsetX = {
                +it
            })
        ) {
            IconButton(
                onClick = { studyPageViewModel.addMyStudyInfo(selectedItems) },
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    tint = DynastyButtonColor,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Composable
fun StudyAllViewItem(
    studyInfo: StudyInfoItem,
    index: Int,
    allStudyData: StudyInfo,
    studyState: String,
    studyPageViewModel: StudyPageViewModel = hiltViewModel(),
) {
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.LightGray else Color.White

    var hintSelected by remember { mutableStateOf(false) }
    val hintText = if (hintSelected) allStudyData[index].description else studyInfo.description

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
            .clickable(
                onClick = {
                    when (studyState) {
                        ORIGIN_STUDY -> {
                            selected = selected.not()
                            with(studyPageViewModel) {
                                if (selected) {
                                    addSelectedItem(studyInfo)
                                } else {
                                    removeSelectedItem(studyInfo)
                                }
                                if (selectedItems.value.size > 0) {
                                    changeButtonState(true)
                                } else {
                                    changeButtonState(false)
                                }
                            }
                        }
                        else -> {
                            selected = selected.not()
                            hintSelected = hintSelected.not()
                        }
                    }
                },
            )
            .background(backgroundColor),

        ) {
        Text(
            text = studyInfo.king_name,
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
            text = hintText,
            fontSize = 25.sp,
            modifier = Modifier
                .border(1.dp, Color.Black, RectangleShape)
                .weight(1f)
                .fillMaxHeight()
                .padding(5.dp),
        )
    }
}