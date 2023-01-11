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
import androidx.compose.ui.draw.alpha
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
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
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
                val data = if (studyState == FIRST_REVIEW && allHintState.not()) {
                    studyData
                } else {
                    allStudyData
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