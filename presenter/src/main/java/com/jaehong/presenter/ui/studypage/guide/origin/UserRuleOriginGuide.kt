package com.jaehong.presenter.ui.studypage.guide.origin

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.jaehong.presenter.theme.BlackWithAlpha50

@Composable
fun UserRuleOriginGuide(
    label: Int,
    swipe: @Composable () -> Unit,
    scroll: @Composable () -> Unit,
    select: @Composable () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BlackWithAlpha50)
    ) {
        when (label) {
            0 -> swipe()
            1 -> scroll()
            2 -> select()
        }
    }
}

