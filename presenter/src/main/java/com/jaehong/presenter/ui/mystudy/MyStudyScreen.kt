package com.jaehong.presenter.ui.mystudy

import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.presenter.ui.mystudy.blank.MyStudyBlankView
import com.jaehong.presenter.ui.mystudy.description.MyDescriptionTextView
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
    val currentPage = myStudyViewModel.currentPage.collectAsState().value
    val pagerList = myStudyViewModel.pagerList.collectAsState().value
    val selectedItems = myStudyViewModel.selectedItems.collectAsState().value
    val dialogState = myStudyViewModel.showDialog.collectAsState().value

    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

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
                                myStudyViewModel.changeButtonState(selectedItems.size)
                            }
                        )
                    }
                }
            )

            DataChangeButton(
                iconType = false,
                isVisible = isVisible,
                size = { myStudyViewModel.getSelectedItemsSize() },
                snackBarState = snackBarState,
                coroutineScope = coroutineScope,
                onIconClicked = { myStudyViewModel.deleteMyStudyInfo(selectedItems) },
                onIconLongClicked = { myStudyViewModel.onOpenDialogClicked() }
            )
            if (dialogState) {
                SaveCheckAlertDialog(
                    dialogType = false,
                    onDialogConfirm = {
                        myStudyViewModel.onDialogConfirm(myStudyData)
                    },
                    onDialogDismiss = {
                        myStudyViewModel.onDialogDismiss()
                    }
                )
            }
        }
    } else MyStudyBlankView { myStudyViewModel.onBackButtonClicked() }
}