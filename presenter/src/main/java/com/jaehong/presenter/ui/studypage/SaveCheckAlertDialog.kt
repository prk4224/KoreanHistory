package com.jaehong.presenter.ui.studypage

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.theme.Typography

@Composable
fun SaveCheckAlertDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { studyPageViewModel.onDialogConfirm() })
            { Text(text = "저장", style = Typography.bodyLarge) }
        },
        dismissButton = {
            TextButton(onClick = { studyPageViewModel.onDialogDismiss() })
            { Text(text = "취소", style = Typography.bodyLarge) }
        },
        title = { Text(text = "복습 노트에 저장하시겠습니까 ?", style = Typography.bodyLarge) },
    )
}