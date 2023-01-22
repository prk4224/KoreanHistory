package com.jaehong.presenter.ui.studypage

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.ui.studypage.description.DescriptionTextView
import com.jaehong.presenter.ui.studypage.dialog.SaveCheckAlertDialog
import com.jaehong.presenter.ui.studypage.guide.blank.SelectRuleBlankDialog
import com.jaehong.presenter.ui.studypage.guide.first.LongClickRuleFirstDialog
import com.jaehong.presenter.ui.studypage.guide.first.SelectRuleFirstDialog
import com.jaehong.presenter.ui.studypage.guide.first.UserRuleFirstGuide
import com.jaehong.presenter.ui.studypage.guide.origin.ScrollRuleOriginDialog
import com.jaehong.presenter.ui.studypage.guide.origin.SelectRuleOriginDialog
import com.jaehong.presenter.ui.studypage.guide.origin.SwipeRuleOriginDialog
import com.jaehong.presenter.ui.studypage.guide.origin.UserRuleOriginGuide
import com.jaehong.presenter.ui.studypage.item.StudyAllViewItem
import com.jaehong.presenter.ui.studypage.item.StudyPageHeaderItem
import com.jaehong.presenter.ui.studypage.pager.StudyPagePagerScreen
import com.jaehong.presenter.util.composable.DataChangeButton
import com.jaehong.presenter.util.enum.StudyType

@Composable
fun StudyPageScreen(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val dynastyState = studyPageViewModel.dynastyState.collectAsState().value
    val studyState = studyPageViewModel.studyState.collectAsState().value
    val studyData = studyPageViewModel.studyInfoList.collectAsState().value
    val isVisible = studyPageViewModel.isVisible.collectAsState().value
    val allStudyData = studyPageViewModel.allStudyInfoList.collectAsState().value
    val dialogState = studyPageViewModel.showDialog.collectAsState().value
    val allHintState = studyPageViewModel.isAllHintVisible.collectAsState().value
    val currentPage = studyPageViewModel.currentPage.collectAsState().value
    val pagerList = studyPageViewModel.pagerList.collectAsState().value
    val originGuideLabel = studyPageViewModel.originGuideLabel.collectAsState().value
    val firstGuideLabel = studyPageViewModel.firstGuideLabel.collectAsState().value
    val blankGuideLabel = studyPageViewModel.blankGuideLabel.collectAsState().value
    val checkedUserRuleOrigin = studyPageViewModel.checkedUserRuleOrigin.collectAsState().value
    val checkedUserRuleFirst = studyPageViewModel.checkedUserRuleFirst.collectAsState().value
    val checkedUserRuleBlank = studyPageViewModel.checkedUserRuleBlank.collectAsState().value

    StudyPagePagerScreen(
        pagerList = pagerList,
        currentPage = currentPage,
        updatePage = { studyPageViewModel.updatePage(it) },
        studyState = studyState,
        allHintState = allHintState,
        studyData = studyData,
        allStudyData = allStudyData,
        header = { type, title -> StudyPageHeaderItem(type, title) },
        dynastyState = dynastyState,
        studyAllViewItem = { studyInfo, index ->
            StudyAllViewItem(studyInfo,) { descIndex, description ->
                DescriptionTextView(
                    studyInfo = studyInfo,
                    description = description,
                    descriptionIndex = descIndex,
                    originDescription = allStudyData[index].description[descIndex],
                    studyState = studyState,
                    changeSelectedItem = { selectedItem, selected ->
                        studyPageViewModel.changeSelectedItem(selectedItem, selected)
                    },
                    changeButtonState = {
                        studyPageViewModel.changeButtonState()
                    },
                    changeAllHintState = {
                        studyPageViewModel.changeAllHintState()
                    }
                )
            }
        }
    )

    DataChangeButton(true, isVisible) {
        studyPageViewModel.onOpenDialogClicked()
    }

    if (dialogState) {
        SaveCheckAlertDialog(
            onDialogConfirm = {
                studyPageViewModel.onDialogConfirm()
            },
            onDialogDismiss = {
                studyPageViewModel.onDialogDismiss()
            }
        )
    }


    when(studyState) {
        StudyType.ORIGIN_STUDY.value -> if(originGuideLabel < 3 && checkedUserRuleOrigin) {
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
                    SelectRuleOriginDialog { label, type, rule ->
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