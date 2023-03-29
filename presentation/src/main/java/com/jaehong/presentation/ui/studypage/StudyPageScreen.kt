package com.jaehong.presentation.ui.studypage

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.StudyType
import com.jaehong.domain.local.model.result.UiStateResult
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
import com.jaehong.presentation.util.composable.CircularProgressBar
import com.jaehong.presentation.util.composable.DataChangeButton
import com.jaehong.presentation.util.dialog.NetworkConnectErrorDialog
import com.jaehong.presentation.util.dialog.SaveCheckAlertDialog
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.util.*

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun StudyPageScreen(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val uiState by studyPageViewModel.uiState.collectAsState()
    val dynastyType by studyPageViewModel.dynastyType.collectAsState()
    val studyType by studyPageViewModel.studyType.collectAsState()
    val firstStudyItems by studyPageViewModel.firstStudyItems.collectAsState()
    val isVisiblePlusBtn by studyPageViewModel.isVisiblePlusBtn.collectAsState()
    val originStudyItems by studyPageViewModel.originStudyItems.collectAsState()
    val isVisibleDialog by studyPageViewModel.isVisibleDialog.collectAsState()
    val isVisibleAllHint by studyPageViewModel.isVisibleAllHint.collectAsState()
    val pageList by studyPageViewModel.pageList.collectAsState()
    val originGuideLabel by studyPageViewModel.originGuideLabel.collectAsState()
    val firstGuideLabel by studyPageViewModel.firstGuideLabel.collectAsState()
    val blankGuideLabel by studyPageViewModel.blankGuideLabel.collectAsState()
    val userRuleState by studyPageViewModel.userRuleState.collectAsState()
    val remoteState by studyPageViewModel.remoteState.collectAsState()
    val connectionState by studyPageViewModel.connectionState.collectAsState()

    //val updatedState = remember { mutableStateOf(false) }
    val selectedItems = remember { mutableStateListOf<StudyInfoItem>() }
    //val updatedItems = remember { mutableStateListOf<StudyInfoItem>() }
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    if (remoteState || connectionState) {
        studyPageViewModel.getStudyData()

        StudyPagePagerScreen(
            pageList = pageList,
            studyType = studyType,
            isVisibleAllHint = isVisibleAllHint,
            firstStudyItems = firstStudyItems,
            //originStudyItems = if (updatedState.value.not()) originStudyItems else updatedItems,
            originStudyItems =  originStudyItems,
            header = { type, title ->
                StudyPageHeaderItem(
                    dynastyState = type,
                    pageTitle = title,
                    //updateState = updatedState.value,
//                    addItem = {
//                        updatedItems.add(StudyInfoItem(makeUUID(), title, "", arrayListOf()))
//                    },
//                    onLongClick = {
//                        if (updatedState.value.not()) updatedItems.addAll(originStudyItems)
//                        updatedState.value = true
//                    }
                )
            },
            dynastyType = dynastyType,
        ) { studyInfo, index ->
//            val map = remember { mutableStateListOf<String>() }
//            studyInfo.description.forEach {
//                if (map.contains(it).not()) map.add(it)
//            }
//            val kingName = remember { mutableStateOf(studyInfo.king_name) }

            StudyAllViewItem(
                studyInfo = studyInfo,
                //list = map,
//                updateState = updatedState.value,
//                kingName = kingName.value,
//                setKingName = { setValue ->
//                    kingName.value = setValue
//                    studyInfo.king_name = setValue
//                },
//                addDescription = {
//                    studyInfo.description.add("")
//                    map.add("")
//                }
            ) { descIndex, description ->

                val selectedItem = StudyInfoItem(
                    studyInfo.id,
                    studyInfo.detail,
                    studyInfo.king_name,
                    arrayListOf(description)
                )

                val selected = selectedItems.contains(selectedItem)
                //val descValue = remember { mutableStateOf(description) }

                DescriptionTextView(
                    studyType = studyType,
                    selectedItem = selectedItem,
                    backgroundColor = if (selected) Gray2 else Color.White,
                    alphaText = if (selected || studyType != StudyType.ALL_BLANK_REVIEW.value) 1f else 0f,
                    hintText = if (selected) originStudyItems[index].description[descIndex] else description,
//                    updateState = updatedState.value,
//                    setDescription = { value ->
//                        studyInfo.description[descIndex] = value
//                        descValue.value = value
//                    },
//                    description = descValue.value,
                    changeSelectedItem = { item ->
                        if (selected) selectedItems.remove(item)
                        else selectedItems.add(item)
                    },
                    checkedButtonState = {
                         studyPageViewModel.checkedButtonState(selectedItems.size)
                    },
                    changeAllHintState = {
                         studyPageViewModel.changeAllHintState()
                    }
                )
            }
        }

        DataChangeButton(
            iconType = true,
            isVisible = isVisiblePlusBtn,
            size = selectedItems.size,
            snackBarState = snackBarState,
            coroutineScope = coroutineScope,
            onIconClicked = {
                selectedItems.clear()
                studyPageViewModel.insertMyStudyItems(selectedItems)
            },
            onIconLongClicked = { studyPageViewModel.onOpenDialogClicked() },
        )

        if (isVisibleDialog) {
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

        when (studyType) {
            StudyType.ORIGIN_STUDY.value -> if (originGuideLabel < 4 && userRuleState) {
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
                            studyPageViewModel.updateUserRule(rule)
                        }
                    }
                )
            }
            StudyType.FIRST_REVIEW.value -> if (firstGuideLabel < 2 && userRuleState) {
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
                            studyPageViewModel.updateUserRule(rule)
                        }
                    }
                )
            }
            StudyType.ALL_BLANK_REVIEW.value -> if (blankGuideLabel && userRuleState) {
                SelectRuleBlankDialog { label, type, rule ->
                    studyPageViewModel.updateLabel(label, type)
                    studyPageViewModel.updateUserRule(rule)
                }
            }
        }

        if (uiState == UiStateResult.LOADING) {
            CircularProgressBar()
        }
    } else {
        NetworkConnectErrorDialog()
    }
}

private fun makeUUID(): String {
    return UUID.randomUUID().toString()
}
