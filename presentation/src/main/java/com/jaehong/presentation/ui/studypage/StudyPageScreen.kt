package com.jaehong.presentation.ui.studypage

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.presentation.theme.Gray2
import com.jaehong.presentation.ui.studypage.description.DescriptionTextView
import com.jaehong.presentation.ui.studypage.guide.blank.SelectRuleBlankDialog
import com.jaehong.presentation.ui.studypage.guide.first.LongClickRuleFirstDialog
import com.jaehong.presentation.ui.studypage.guide.first.SelectRuleFirstDialog
import com.jaehong.presentation.ui.studypage.guide.first.UserRuleFirstGuide
import com.jaehong.presentation.ui.studypage.guide.origin.*
import com.jaehong.presentation.ui.studypage.item.StudyAllViewItem
import com.jaehong.presentation.ui.studypage.item.StudyPageHeaderItem
import com.jaehong.presentation.ui.studypage.pager.StudyPagePagerScreen
import com.jaehong.presentation.util.composable.DataChangeButton
import com.jaehong.presentation.util.dialog.SaveCheckAlertDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun StudyPageScreen(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState by studyPageViewModel.dynastyState.collectAsState()
    val studyState by studyPageViewModel.studyState.collectAsState()
    val studyData by studyPageViewModel.studyInfoList.collectAsState()
    val isVisible by studyPageViewModel.isVisible.collectAsState()
    val allStudyData by studyPageViewModel.allStudyInfoList.collectAsState()
    val dialogState by studyPageViewModel.showDialog.collectAsState()
    val allHintState by studyPageViewModel.isAllHintVisible.collectAsState()
    val pagerList by studyPageViewModel.pagerList.collectAsState()
    val originGuideLabel by studyPageViewModel.originGuideLabel.collectAsState()
    val firstGuideLabel by studyPageViewModel.firstGuideLabel.collectAsState()
    val blankGuideLabel by studyPageViewModel.blankGuideLabel.collectAsState()
    val checkedUserRuleOrigin by studyPageViewModel.checkedUserRuleOrigin.collectAsState()
    val checkedUserRuleFirst by studyPageViewModel.checkedUserRuleFirst.collectAsState()
    val checkedUserRuleBlank by studyPageViewModel.checkedUserRuleBlank.collectAsState()

    val selectedItems = remember { mutableStateListOf<StudyInfoItem>() }
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    StudyPagePagerScreen(
        pagerList = pagerList,
        studyState = studyState,
        allHintState = allHintState,
        studyData = studyData,
        allStudyData = allStudyData,
        header = { type, title -> StudyPageHeaderItem(type, title) },
        dynastyState = dynastyState
    ) { studyInfo, index ->
        StudyAllViewItem(studyInfo) { descIndex, description ->
            val selectedItem = StudyInfoItem(
                studyInfo.id+descIndex,
                studyInfo.detail,
                studyInfo.king_name,
                arrayListOf(studyInfo.description[descIndex])
            )

            val selected = selectedItems.contains(selectedItem)

            DescriptionTextView(
                studyState = studyState,
                selectedItem = selectedItem,
                backgroundColor = if (selected) Gray2 else Color.White,
                alphaText = if (selected || studyState != StudyType.ALL_BLANK_REVIEW.value) 1f else 0f,
                hintText = if (selected) allStudyData[index].description[descIndex] else description,
                changeSelectedItem = { item ->
                    if (selected) selectedItems.remove(item)
                    else selectedItems.add(item)
                },
                changeButtonState = { studyPageViewModel.changeButtonState(selectedItems.size) },
                changeAllHintState = { studyPageViewModel.changeAllHintState() })
        }
    }

    DataChangeButton(
        iconType = true,
        isVisible = isVisible,
        size = selectedItems.size,
        snackBarState = snackBarState,
        coroutineScope = coroutineScope,
        onIconClicked = {
            studyPageViewModel.addSelectedItems(selectedItems)
            selectedItems.clear()
        },
        onIconLongClicked = { studyPageViewModel.onOpenDialogClicked() },
    )

    if (dialogState) {
        SaveCheckAlertDialog(
            dialogType = true,
            onDialogConfirm = {
                studyPageViewModel.onDialogConfirm()
                selectedItems.clear()
            },
            onDialogDismiss = {
                studyPageViewModel.onDialogDismiss()
            }
        )
    }

    when(studyState) {
        StudyType.ORIGIN_STUDY.value -> if(originGuideLabel < 4 && checkedUserRuleOrigin) {
            UserRuleOriginGuide(
                label = originGuideLabel,
                swipe = {
                    SwipeRuleOriginDialog { label, type ->
                        studyPageViewModel.updateLabel(label, type)
                    }
                },
                scroll = {
                    ScrollRuleOriginDialog { label, type ->
                        studyPageViewModel.updateLabel(label, type)
                    }
                },
                select = {
                    SelectRuleOriginDialog { label, type ->
                        studyPageViewModel.updateLabel(label, type)
                    }
                },
                allSave = {
                    AllSaveRuleOriginDialog { label, type, rule ->
                        studyPageViewModel.updateLabel(label, type)
                        studyPageViewModel.setUserRule(rule)
                    }
                }
            )
        }
        StudyType.FIRST_REVIEW.value -> if(firstGuideLabel < 2 && checkedUserRuleFirst) {
            UserRuleFirstGuide(
                firstGuideLabel,
                select = {
                    SelectRuleFirstDialog { label, type ->
                        studyPageViewModel.updateLabel(label, type)
                    }
                },
                longClick = {
                    LongClickRuleFirstDialog { label, type, rule ->
                        studyPageViewModel.updateLabel(label, type)
                        studyPageViewModel.setUserRule(rule)
                    }
                }
            )
        }
        StudyType.ALL_BLANK_REVIEW.value -> if(blankGuideLabel && checkedUserRuleBlank) {
            SelectRuleBlankDialog { label, type, rule ->
                studyPageViewModel.updateLabel(label, type)
                studyPageViewModel.setUserRule(rule)
            }
        }
    }
}