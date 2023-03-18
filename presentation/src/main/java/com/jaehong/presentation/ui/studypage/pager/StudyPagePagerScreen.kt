package com.jaehong.presentation.ui.studypage.pager

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
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.presentation.theme.Gray3

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StudyPagePagerScreen(
    pageList: List<String>,
    dynastyType: String,
    studyType: String,
    isVisibleAllHint: Boolean,
    firstStudyItems: List<StudyInfoItem>,
    originStudyItems: List<StudyInfoItem>,
    header: @Composable (String, String) -> Unit,
    studyAllViewItem: @Composable (StudyInfoItem, Int) -> Unit,
) {
    HorizontalPager(
        count = pageList.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Gray3),
        verticalAlignment = Alignment.Top,
    ) { page ->
        LazyColumn(
            modifier = Modifier
                .padding(30.dp)
        ) {
            val data = if (studyType == StudyType.FIRST_REVIEW.value && isVisibleAllHint.not()) {
                firstStudyItems
            } else {
                originStudyItems
            }
            item {
                header(dynastyType, pageList[page])
            }
            itemsIndexed(data) { index, studyInfo ->
                if (studyInfo.detail == pageList[page]) {
                    studyAllViewItem(studyInfo, index)
                }
            }
        }
    }
}