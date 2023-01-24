package com.jaehong.presenter.ui.mystudy

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.ui.mystudy.blank.MyStudyBlankView
import com.jaehong.presenter.ui.mystudy.description.MyDescriptionTextView
import com.jaehong.presenter.ui.mystudy.item.MyStudyHeaderItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyNoticeItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyViewItem
import com.jaehong.presenter.ui.mystudy.pager.MyStudyPagerScreen
import com.jaehong.presenter.util.composable.DataChangeButton
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
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
                    MyStudyViewItem(it) { studyInfo, description, descIndex ->
                        val (selected,setSelected) = remember(description) { mutableStateOf(false) }
                        MyDescriptionTextView(
                            description = description,
                            selectedItem = StudyInfoItem(
                                studyInfo.id + descIndex,
                                studyInfo.detail,
                                studyInfo.king_name,
                                arrayListOf(description)
                            ),
                            selected = selected,
                            onSelectChange = setSelected,
                            onTextClicked = { selectedItem, select ->
                                myStudyViewModel.changeSelectedItem(selectedItem, select)
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
    } else MyStudyBlankView { myStudyViewModel.onBackButtonClicked() }
}