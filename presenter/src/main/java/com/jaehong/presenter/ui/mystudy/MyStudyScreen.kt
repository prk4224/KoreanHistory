package com.jaehong.presenter.ui.mystudy

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.jaehong.presenter.theme.BaseColor1
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.theme.Gray3
import com.jaehong.presenter.theme.MyStudyColor
import com.jaehong.presenter.util.Constants.MY_KEYWORD
import com.jaehong.presenter.util.Constants.MY_STUDY_TEXT
import com.jaehong.presenter.util.FontFixed.nonScaledSp

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
        Box {
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
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            modifier = Modifier
                                .height(60.dp)
                                .background(MyStudyColor, RoundedCornerShape(50, 50, 0, 0))
                        ){
                            Text(
                                text = MY_KEYWORD,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 35.nonScaledSp,
                                color = Color.White
                            )
                            Text(
                                text ="(${pagerList[page]})",
                                modifier = Modifier
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Center,
                                fontSize = 24.nonScaledSp,
                                color = Color.White
                            )
                        }
                    }
                    item{
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .border(1.dp, Color.LightGray, RectangleShape)
                                .background(Gray2)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = MY_STUDY_TEXT,
                                fontSize = 24.nonScaledSp,
                                modifier = Modifier
                                    .fillMaxWidth()
                            )
                        }
                    }

                    items(myStudyData) { studyInfo ->
                        if (pagerList[page] == studyInfo.detail) {
                            MyStudyViewItem(studyInfo)
                        }
                    }
                }
            }

            AnimatedVisibility(
                visible = isVisible,
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .padding(10.dp),
                enter = slideInHorizontally(initialOffsetX = {
                    +it
                }),
                exit = slideOutHorizontally(targetOffsetX = {
                    +it
                })
            ) {
                IconButton(
                    onClick = { myStudyViewModel.deleteMyStudyInfo(selectedItems) },
                ) {
                    Icon(
                        imageVector = Icons.Filled.RemoveCircle,
                        tint = BaseColor1,
                        contentDescription = null,
                        modifier = Modifier
                            .size(150.dp)
                            .background(Color.White)
                    )
                }
            DataChangeButton(false,isVisible) {
                myStudyViewModel.deleteMyStudyInfo(selectedItems)
            }

        }
    } else MyStudyBlankView()
}