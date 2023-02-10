package com.jaehong.presenter.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.sp

object FontFixed {
    val Int.nonScaledSp
        @Composable
        get() = (this / LocalDensity.current.fontScale).sp
}