package com.jaehong.presenter.ui.studypage

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun SaveCheckAlertDialog(
    selectedSize: Int,
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    val snackBarState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = {
                studyPageViewModel.onDialogConfirm()
                coroutineScope.launch {
                    snackBarState.showSnackbar("$selectedSize 개 저장 완료 !")
                }
            })
            { Text(text = "저장", style = Typography.bodyLarge) }
        },
        dismissButton = {
            TextButton(onClick = { studyPageViewModel.onDialogDismiss() })
            { Text(text = "취소", style = Typography.bodyLarge) }
        },
        title = { Text(text = "복습 노트에 저장하시겠습니까 ?", style = Typography.bodyLarge) },
    )
    //SnackbarHost(hostState = snackBarState, modifier = Modifier.align(Alignment.BottomCenter))
}