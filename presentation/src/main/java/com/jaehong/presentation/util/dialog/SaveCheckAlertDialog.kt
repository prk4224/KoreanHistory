package com.jaehong.presentation.util.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import com.jaehong.presentation.theme.Typography
import com.jaehong.presentation.util.Constants.CANCEL_TEXT
import com.jaehong.presentation.util.Constants.REMOVE_DIALOG_MESSAGE
import com.jaehong.presentation.util.Constants.REMOVE_TEXT
import com.jaehong.presentation.util.Constants.SAVE_DIALOG_MESSAGE
import com.jaehong.presentation.util.Constants.SAVE_TEXT
import com.jaehong.presentation.util.FontFixed.nonScaledSp

@Composable
fun SaveCheckAlertDialog(
    dialogType: Boolean,
    textButton: String = if (dialogType) SAVE_TEXT else REMOVE_TEXT,
    textTitle: String = if (dialogType) SAVE_DIALOG_MESSAGE else REMOVE_DIALOG_MESSAGE,
    onDialogConfirm: () -> Unit,
    onDialogDismiss: () -> Unit,
) {
    AlertDialog(
        onDismissRequest = { },
        confirmButton = {
            TextButton(onClick = { onDialogConfirm() }) {
                Text(
                    text = textButton,
                    style = Typography.bodyLarge,
                    fontSize = 22.nonScaledSp
                )
            }
        },
        dismissButton = {
            TextButton(onClick = { onDialogDismiss() }) {
                Text(
                    text = CANCEL_TEXT,
                    style = Typography.bodyLarge,
                    fontSize = 22.nonScaledSp
                )
            }
        },
        title = {
            Text(
                text = textTitle,
                style = Typography.bodyLarge,
                fontSize = 22.nonScaledSp
            )
        },
    )
}