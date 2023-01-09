package com.jaehong.presenter.ui.mystudy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
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
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.DynastyButtonColor
import com.jaehong.presenter.util.Constants.MY_KEYWORD

@Composable
fun MyStudyScreen(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val myStudyData = myStudyViewModel.myStudyInfoList.collectAsState().value
    val isVisible = myStudyViewModel.isVisible.collectAsState().value
    val selectedItems = myStudyViewModel.selectedItems.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        LazyColumn(
            modifier = Modifier.padding(30.dp),
        ) {
            item {
                Text(
                    text = MY_KEYWORD,
                    modifier = Modifier
                        .background(DynastyButtonColor, RoundedCornerShape(50, 50, 0, 0))
                        .fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    fontSize = 30.sp,
                    color = Color.White
                )
            }

            items(myStudyData) { studyInfo ->
                MyStudyViewItem(studyInfo)
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
                onClick = {
                    myStudyViewModel.deleteMyStudyInfo(selectedItems)
                },
            ) {
                Icon(
                    imageVector = Icons.Filled.RemoveCircle,
                    tint = DynastyButtonColor,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
        }
    }
}

@Composable
fun MyStudyViewItem(
    studyInfo: StudyInfoItem,
) {
    val selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.LightGray else Color.White

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Max)
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
        Column(
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight(),
        ) {
            studyInfo.description.forEachIndexed { index, description ->
                MyDescriptionTextView(studyInfo, description, index)
            }
        }
    }
}

@Composable
fun MyDescriptionTextView(
    studyInfo: StudyInfoItem,
    description: String,
    descriptionIndex: Int,
    myStudyViewModel: MyStudyViewModel = hiltViewModel(),
) {
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.LightGray else Color.White

    Text(
        text = description,
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, RectangleShape)
            .padding(5.dp)
            .background(backgroundColor)
            .clickable(
                onClick = {
                    with(myStudyViewModel) {
                        selected = selected.not()

                        val selectedItem = StudyInfoItem(
                            studyInfo.id + descriptionIndex,
                            studyInfo.detail,
                            studyInfo.king_name,
                            arrayListOf(description)
                        )

                        if (selected) {
                            addSelectedItem(selectedItem)
                        } else {
                            removeSelectedItem(selectedItem)
                        }
                        if (selectedItems.value.size > 0) {
                            changeButtonState(true)
                        } else {
                            changeButtonState(false)
                        }
                    }
                },
            )
    )
}