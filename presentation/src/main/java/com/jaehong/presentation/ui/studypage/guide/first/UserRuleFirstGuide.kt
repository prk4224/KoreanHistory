package com.jaehong.presentation.ui.studypage.guide.first

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaehong.presentation.theme.BlackWithAlpha50

@Composable
fun UserRuleFirstGuide(
    label: Int,
    select: @Composable () -> Unit,
    longClick: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackWithAlpha50)
    ) {
        when (label) {
            0 -> select()
            1 -> longClick()
        }
    }
}

