package com.jaehong.presenter.ui.studypage.guide.first

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaehong.presenter.theme.BlackWithAlpha50

@Composable
fun UserRuleFirstGuide(label: Int) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackWithAlpha50)
    ) {
        when (label) {
            0 -> SelectRuleFirstDialog()
            1 -> LongClickRuleFirstDialog()
        }
    }
}

