package com.jaehong.presenter.ui.studypage.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray3
import com.jaehong.presenter.util.enum.StudyType

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudyPagePagerScreen(
    pagerList: List<String>,
    currentPage: Int,
    updatePage: (Int) -> Unit,
    dynastyState: String,
    studyState: String,
    allHintState: Boolean,
    studyData: List<StudyInfoItem>,
    allStudyData: List<StudyInfoItem>,
    header: @Composable (String, String) -> Unit,
    studyAllViewItem: @Composable (StudyInfoItem, Int) -> Unit
) {
    HorizontalPager(
        count = pagerList.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Gray3),
        verticalAlignment = Alignment.Top,
        state = rememberPagerState(initialPage = currentPage)
    ) { page ->
        if (currentPage != this.currentPage) {
            updatePage(this.currentPage)
        }

        LazyColumn(
            modifier = Modifier.padding(30.dp)
        ) {
            val data = if (studyState == StudyType.FIRST_REVIEW.value && allHintState.not()) {
                studyData
            } else {
                allStudyData
            }
            item {
                header(dynastyState, pagerList[page])
            }

            itemsIndexed(data) { index, studyInfo ->
                if (studyInfo.detail == pagerList[page]) {
                    studyAllViewItem(studyInfo, index)
                }
            }
        }
    }
}