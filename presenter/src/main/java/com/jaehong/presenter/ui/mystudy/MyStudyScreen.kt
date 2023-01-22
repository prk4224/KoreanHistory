package com.jaehong.presenter.ui.mystudy

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.ui.mystudy.blank.MyStudyBlankView
import com.jaehong.presenter.ui.mystudy.description.MyDescriptionTextView
import com.jaehong.presenter.ui.mystudy.item.MyStudyHeaderItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyNoticeItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyViewItem
import com.jaehong.presenter.ui.mystudy.pager.MyStudyPagerScreen
import com.jaehong.presenter.util.composable.DataChangeButton

@Composable
fun MyStudyScreen(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val myStudyData = myStudyViewModel.myStudyInfoList.collectAsState().value
    val isVisible = myStudyViewModel.isVisible.collectAsState().value
    val currentPage = myStudyViewModel.currentPage.collectAsState().value
    val pagerList = myStudyViewModel.pagerList.collectAsState().value
    val selectedItems = myStudyViewModel.selectedItems.collectAsState().value

    val blankImage = painterResource(id = R.drawable.black_image)

    if(pagerList.isNotEmpty()){
        Surface {
            MyStudyPagerScreen(
                pagerList = pagerList,
                myStudyData = myStudyData,
                currentPage = currentPage,
                updatePage = {
                    myStudyViewModel.updatePage(it)
                },
                myStudyHeader = {
                    MyStudyHeaderItem(it)
                },
                myStudyNotice = {
                    MyStudyNoticeItem()
                },
                myStudyView = {
                    MyStudyViewItem(it) { studyInfo, description, index ->
                        MyDescriptionTextView(
                            studyInfo = studyInfo,
                            description = description,
                            descriptionIndex = index,
                            onTextClicked = { selectedItem, selected ->
                                myStudyViewModel.changeSelectedItem(selectedItem, selected)
                                myStudyViewModel.changeButtonState()
                            }
                        )
                    }
                }
            )

            DataChangeButton(false, isVisible) {
                myStudyViewModel.deleteMyStudyInfo(selectedItems)
            }
        }
    } else MyStudyBlankView(
        blankImage = blankImage,
        onBackButtonClicked = { myStudyViewModel.onBackButtonClicked() }
    )
}