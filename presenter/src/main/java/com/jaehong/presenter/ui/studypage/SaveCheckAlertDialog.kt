package com.jaehong.presenter.ui.studypage

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.theme.Typography
import com.jaehong.presenter.util.Constants.CANCEL_TEXT
import com.jaehong.presenter.util.Constants.SAVE_DIALOG_MESSAGE
import com.jaehong.presenter.util.Constants.SAVE_TEXT
import com.jaehong.presenter.util.FontFixed.nonScaledSp

@Composable
fun SaveCheckAlertDialog(
    studyPageViewModel: StudyPageViewModel = hiltViewModel()
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { studyPageViewModel.onDialogConfirm() }) {
                Text(
                    text = SAVE_TEXT,
                    style = Typography.bodyLarge,
                    fontSize = 22.nonScaledSp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { studyPageViewModel.onDialogDismiss() }) {
                Text(
                    text = CANCEL_TEXT,
                    style = Typography.bodyLarge,
                    fontSize = 22.nonScaledSp
                )
            }
        },
        title = {
            Text(
                text = SAVE_DIALOG_MESSAGE,
                style = Typography.bodyLarge,
                fontSize = 22.nonScaledSp
            )
        },
    )
}