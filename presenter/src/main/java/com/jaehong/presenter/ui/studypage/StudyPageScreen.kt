package com.jaehong.presenter.ui.studypage

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.theme.Gray3
import com.jaehong.presenter.ui.studypage.guide.blank.SelectRuleBlankDialog
import com.jaehong.presenter.ui.studypage.guide.first.UserRuleFirstGuide
import com.jaehong.presenter.ui.studypage.guide.origin.UserRuleOriginGuide
import com.jaehong.presenter.util.Constants.ALL_BLANK_REVIEW
import com.jaehong.presenter.util.Constants.FIRST_REVIEW
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY
import com.jaehong.presenter.util.DataChangeButton
import com.jaehong.presenter.util.FontFixed.nonScaledSp

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
    val originGuideLabel = studyPageViewModel.originGuideLabel.collectAsState().value
    val firstGuideLabel = studyPageViewModel.firstGuideLabel.collectAsState().value
    val blankGuideLabel = studyPageViewModel.blankGuideLabel.collectAsState().value
    val checkedUserRuleOrigin = studyPageViewModel.checkedUserRuleOrigin.collectAsState().value
    val checkedUserRuleFirst = studyPageViewModel.checkedUserRuleFirst.collectAsState().value
    val checkedUserRuleBlank = studyPageViewModel.checkedUserRuleBlank.collectAsState().value


    Box {
        HorizontalPager(
            count = pagerList.size,
            modifier = Modifier
                .fillMaxSize()
                .background(Gray3),
            verticalAlignment = Alignment.Top,
            state = rememberPagerState(initialPage = currentPage)
        ) { page ->
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
                    StudyPageHeaderItem(dynastyState,pagerList[page])
                }
                itemsIndexed(data) { index, studyInfo ->
                    if(studyInfo.detail == pagerList[page]){
                        StudyAllViewItem(studyInfo, index, allStudyData, studyState)
                    }
                }
            }
        }

        DataChangeButton(true,isVisible) {
            studyPageViewModel.onOpenDialogClicked()
        }

        if (dialogState) {
            SaveCheckAlertDialog()
        }
    }

    when(studyState) {
        ORIGIN_STUDY -> if(originGuideLabel < 3 && checkedUserRuleOrigin) {
            UserRuleOriginGuide(originGuideLabel)
        }
        FIRST_REVIEW -> if(firstGuideLabel < 2 && checkedUserRuleFirst) {
            UserRuleFirstGuide(firstGuideLabel)
        }
        ALL_BLANK_REVIEW -> if(blankGuideLabel && checkedUserRuleBlank) {
            SelectRuleBlankDialog()
        }
    }
}