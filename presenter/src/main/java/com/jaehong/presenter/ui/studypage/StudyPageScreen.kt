package com.jaehong.presenter.ui.studypage

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.GenericShape
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
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.DynastyButtonColor
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudyPageScreen(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState = studyPageViewModel.dynastyState.collectAsState().value
    val studyState = studyPageViewModel.studyState.collectAsState().value
    val studyData = studyPageViewModel.studyInfoList.collectAsState().value
    val isVisible = studyPageViewModel.isVisible.collectAsState().value
    val allStudyData = studyPageViewModel.allStudyInfoList.collectAsState().value
    val dialogState = studyPageViewModel.showDialog.collectAsState().value
    val allHintState = studyPageViewModel.isAllHintVisible.collectAsState().value
    val currentPage = studyPageViewModel.currentPage.collectAsState().value
    val pagerList = studyPageViewModel.pagerList.collectAsState().value

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray),
    ) {
        HorizontalPager(count = pagerList.size) { page ->
            if(currentPage != this.currentPage){
                studyPageViewModel.updatePage(this.currentPage)
            }
            LazyColumn(
                modifier = Modifier.padding(30.dp),
            ) {
                val data = if (studyState == ORIGIN_STUDY || allHintState) {
                    allStudyData
                } else {
                    studyData
                }

                item {
                    Text(
                        text = "$dynastyState - ${pagerList[page]}",
                        modifier = Modifier
                            .background(DynastyButtonColor, RoundedCornerShape(50, 50, 0, 0))
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontSize = 30.sp,
                        color = Color.White
                    )
                }
                itemsIndexed(data) { index, studyInfo ->
                    if(studyInfo.detail == pagerList[page]){
                        StudyAllViewItem(studyInfo, index, allStudyData, studyState)
                    }
                }
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
                onClick = { studyPageViewModel.onOpenDialogClicked() },
            ) {
                Icon(
                    imageVector = Icons.Filled.AddCircle,
                    tint = DynastyButtonColor,
                    contentDescription = null,
                    modifier = Modifier.size(150.dp)
                )
            }
        }

        if (dialogState) {
            SaveCheckAlertDialog()
        }
    }
}

@Composable
fun StudyAllViewItem(
    studyInfo: StudyInfoItem,
    index: Int,
    allStudyData: StudyInfo,
    studyState: String,
) {
    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .background(Color.White),
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

        Column(modifier = Modifier
            .weight(1f)
            .border(1.dp, Color.Black, RectangleShape)
            .fillMaxHeight()
            ,) {
            studyInfo.description.forEachIndexed { descIndex, description ->
                DescriptionTextView(
                    studyInfo,
                    description,
                    index,
                    descIndex,
                    allStudyData,
                    studyState
                )
            }
        }
    }
}

@Composable
fun DescriptionTextView(
    studyInfo: StudyInfoItem,
    description: String,
    studyIndex: Int,
    descriptionIndex: Int,
    allStudyData: StudyInfo,
    studyState: String,
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    var selected by remember { mutableStateOf(false) }
    val backgroundColor = if (selected) Color.LightGray else Color.White

    var hintSelected by remember { mutableStateOf(false) }
    val hintText = if (hintSelected) allStudyData[studyIndex].description[descriptionIndex] else description

    Text(
        text = hintText,
        fontSize = 25.sp,
        modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black, getTopLineShape())
            .padding(5.dp)
            .background(backgroundColor)
            .pointerInput(Unit) {
                detectTapGestures(
                    onTap = {
                        when (studyState) {
                            ORIGIN_STUDY -> {
                                selected = selected.not()
                                with(studyPageViewModel) {
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
                            }
                            else -> {
                                selected = selected.not()
                                hintSelected = hintSelected.not()
                            }
                        }
                    },
                    onLongPress = {
                        studyPageViewModel.changeAllHintState()
                    }
                )
            }

    )
}

private fun getTopLineShape() : Shape {
    return GenericShape { size, _ ->
        // 1) Bottom-left corner
        moveTo(0f, 0f )
        // 2) Bottom-right corner
        lineTo(size.width, 0f )
        // 3) Top-right corner
        lineTo(size.width, 0f - 2f)
        // 4) Top-left corner
        lineTo(0f, 0f - 2f )
    }
}



