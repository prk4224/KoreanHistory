package com.jaehong.presenter.ui.studypage.guide

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Dialog

@Composable
fun UserRuleGuide(label: Int) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Dialog(
            onDismissRequest = { /*TODO*/ },
        ) {
            when(label) {
                0 -> SwipeRuleDialog()
                1 -> ScrollRuleDialog()
                2 -> SelectRuleDialog()
            }
        }
    }
}

