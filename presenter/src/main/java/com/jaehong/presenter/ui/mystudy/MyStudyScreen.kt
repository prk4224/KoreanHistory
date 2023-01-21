package com.jaehong.presenter.ui.mystudy

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.jaehong.presenter.theme.Gray3
import com.jaehong.presenter.util.DataChangeButton

@OptIn(ExperimentalPagerApi::class)
@Composable
fun MyStudyScreen(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val myStudyData = myStudyViewModel.myStudyInfoList.collectAsState().value
    val isVisible = myStudyViewModel.isVisible.collectAsState().value
    val currentPage = myStudyViewModel.currentPage.collectAsState().value
    val pagerList = myStudyViewModel.pagerList.collectAsState().value
    val selectedItems = myStudyViewModel.selectedItems.collectAsState().value

    if(pagerList.isNotEmpty()){
        Surface {
            HorizontalPager(
                count = pagerList.size,
                modifier = Modifier
                    .fillMaxSize()
                    .background(Gray3),
                verticalAlignment = Alignment.Top,
            ) { page ->
                if(currentPage != this.currentPage){
                    myStudyViewModel.updatePage(this.currentPage)
                }
                LazyColumn(
                    modifier = Modifier.padding(vertical = 50.dp, horizontal = 20.dp),
                ) {
                    item {
                        MyStudyHeaderItem(pagerList[page])
                    }
                    item{
                        MyStudyNoticeItem()
                    }
                    items(myStudyData) { studyInfo ->
                        if (pagerList[page] == studyInfo.detail) {
                            MyStudyViewItem(studyInfo)
                        }
                    }
                }
            }
            DataChangeButton(false,isVisible) {
                myStudyViewModel.deleteMyStudyInfo(selectedItems)
            }
        }
    } else MyStudyBlankView()
}