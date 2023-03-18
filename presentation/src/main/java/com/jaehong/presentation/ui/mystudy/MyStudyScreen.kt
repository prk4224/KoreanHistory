package com.jaehong.presentation.ui.mystudy

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presentation.theme.Gray2
import com.jaehong.presentation.ui.mystudy.blank.MyStudyBlankView
import com.jaehong.presentation.ui.mystudy.description.MyDescriptionTextView
import com.jaehong.presentation.ui.mystudy.guide.AllRemoveRuleDialog
import com.jaehong.presentation.ui.mystudy.item.MyStudyHeaderItem
import com.jaehong.presentation.ui.mystudy.item.MyStudyNoticeItem
import com.jaehong.presentation.ui.mystudy.item.MyStudyViewItem
import com.jaehong.presentation.ui.mystudy.pager.MyStudyPagerScreen
import com.jaehong.presentation.util.composable.DataChangeButton
import com.jaehong.presentation.util.dialog.SaveCheckAlertDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun MyStudyScreen(
    myStudyViewModel: MyStudyViewModel = hiltViewModel()
) {
    val myStudyItems by myStudyViewModel.myStudyItems.collectAsState()
    val isVisibleMinusBtn by myStudyViewModel.isVisibleMinusBtn.collectAsState()
    val pageList by myStudyViewModel.pageList.collectAsState()
    val isVisibleDialog by myStudyViewModel.isVisibleDialog.collectAsState()
    val userRuleState by myStudyViewModel.userRuleState.collectAsState()

    val selectedItems = remember { mutableStateListOf<StudyInfoItem>() }
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    if(pageList.isNotEmpty()){
        Surface {
            MyStudyPagerScreen(
                pageList = pageList,
                myStudyItems = myStudyItems,
                myStudyHeader = {
                    MyStudyHeaderItem(it)
                },
                myStudyNotice = {
                    MyStudyNoticeItem()
                },
                myStudyView = {
                    MyStudyViewItem(it) { studyInfo, description ->
                        val selectedItem = StudyInfoItem(
                            studyInfo.id,
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
                                myStudyViewModel.checkedButtonState(selectedItems.size)
                            }
                        )
                    }
                }
            )

            DataChangeButton(
                iconType = false,
                isVisible = isVisibleMinusBtn,
                size = selectedItems.size ,
                snackBarState = snackBarState,
                coroutineScope = coroutineScope,
                onIconClicked = {
                    myStudyViewModel.deleteMyStudyItems(selectedItems)
                    selectedItems.clear()
                },
                onIconLongClicked = { myStudyViewModel.onOpenDialogClicked() }
            )
            if (isVisibleDialog) {
                SaveCheckAlertDialog(
                    dialogType = false,
                    onDialogConfirm = {
                        myStudyViewModel.onDialogConfirm(myStudyItems)
                        selectedItems.clear()
                    },
                    onDialogDismiss = {
                        myStudyViewModel.onDialogDismiss()
                    }
                )
            }
            if(userRuleState) {
                AllRemoveRuleDialog {
                    myStudyViewModel.setUserRule(it)
                }
            }
        }
    } else MyStudyBlankView { myStudyViewModel.onBackButtonClicked() }
}