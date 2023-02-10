package com.jaehong.presenter.ui.mystudy

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.theme.Gray2
import com.jaehong.presenter.ui.mystudy.blank.MyStudyBlankView
import com.jaehong.presenter.ui.mystudy.description.MyDescriptionTextView
import com.jaehong.presenter.ui.mystudy.guide.AllRemoveRuleDialog
import com.jaehong.presenter.ui.mystudy.item.MyStudyHeaderItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyNoticeItem
import com.jaehong.presenter.ui.mystudy.item.MyStudyViewItem
import com.jaehong.presenter.ui.mystudy.pager.MyStudyPagerScreen
import com.jaehong.presenter.util.composable.DataChangeButton
import com.jaehong.presenter.util.dialog.SaveCheckAlertDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MyStudyScreen(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val myStudyData = myStudyViewModel.myStudyInfoList.collectAsState().value
    val isVisible = myStudyViewModel.isVisible.collectAsState().value
    val pagerList = myStudyViewModel.pagerList.collectAsState().value
    val dialogState = myStudyViewModel.showDialog.collectAsState().value
    val checkedUserRule = myStudyViewModel.checkedUserRule.collectAsState().value

    val selectedItems = remember { mutableStateListOf<StudyInfoItem>() }
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    if(pagerList.isNotEmpty()){
        Surface {
            MyStudyPagerScreen(
                pagerList = pagerList,
                myStudyData = myStudyData,
                myStudyHeader = {
                    MyStudyHeaderItem(it)
                },
                myStudyNotice = {
                    MyStudyNoticeItem()
                },
                myStudyView = {
                    MyStudyViewItem(it) { studyInfo, description, descIndex ->
                        val selectedItem = StudyInfoItem(
                            studyInfo.id + descIndex,
                            studyInfo.detail,
                            studyInfo.king_name,
                            arrayListOf(description)
                        )

                        val selected = selectedItems.contains(selectedItem)

                        MyDescriptionTextView(
                            description = description,
                            selectedItem = selectedItem,
                            backgroundColor = if (selected) Gray2 else Color.White,
                            onTextClicked = { item ->
                                if(selected) selectedItems.remove(item)
                                else selectedItems.add(item)
                                myStudyViewModel.changeButtonState(selectedItems.size)
                            }
                        )
                    }
                }
            )

            DataChangeButton(
                iconType = false,
                isVisible = isVisible,
                size = selectedItems.size ,
                snackBarState = snackBarState,
                coroutineScope = coroutineScope,
                onIconClicked = {
                    myStudyViewModel.deleteMyStudyInfo(selectedItems)
                    selectedItems.clear()
                },
                onIconLongClicked = { myStudyViewModel.onOpenDialogClicked() }
            )
            if (dialogState) {
                SaveCheckAlertDialog(
                    dialogType = false,
                    onDialogConfirm = {
                        myStudyViewModel.onDialogConfirm(myStudyData)
                        selectedItems.clear()
                    },
                    onDialogDismiss = {
                        myStudyViewModel.onDialogDismiss()
                    }
                )
            }
            if(checkedUserRule) {
                AllRemoveRuleDialog {
                    myStudyViewModel.setUserRule(it)
                }
            }
        }
    } else MyStudyBlankView { myStudyViewModel.onBackButtonClicked() }
}