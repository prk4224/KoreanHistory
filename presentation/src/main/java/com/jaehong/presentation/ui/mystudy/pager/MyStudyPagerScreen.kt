package com.jaehong.presentation.ui.mystudy.pager

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presentation.theme.Gray3

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyStudyPagerScreen(
    pagerList: List<String>,
    myStudyData: List<StudyInfoItem>,
    myStudyHeader: @Composable (String) -> Unit,
    myStudyNotice: @Composable () -> Unit,
    myStudyView: @Composable (StudyInfoItem) -> Unit,
) {
    HorizontalPager(
        count = pagerList.size,
        modifier = Modifier
            .fillMaxSize()
            .background(Gray3),
        verticalAlignment = Alignment.Top,
    ) { page ->

        LazyColumn(
            modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp),
        ) {
            item {
                myStudyHeader(pagerList[page])
            }
            item {
                myStudyNotice()
            }
            items(myStudyData) { studyInfo ->
                if (pagerList[page] == studyInfo.detail) {
                    myStudyView(studyInfo)
                }
            }
        }
    }
}