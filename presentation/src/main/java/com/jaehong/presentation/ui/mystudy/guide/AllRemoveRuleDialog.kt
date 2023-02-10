package com.jaehong.presentation.ui.mystudy.guide

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.presentation.R
import com.jaehong.presentation.theme.BlackWithAlpha50

@Composable
fun AllRemoveRuleDialog(
    allRemoveImage: Painter = painterResource(id = R.drawable.all_remove_image),
    onClickUpdate: (String) -> Unit,
) {

    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackWithAlpha50)
        .clickable(
            onClick = {
                onClickUpdate(GuideKey.USER_RULE_MY_PAGE.value)
            }
        )
    ) {
        Image(
            painter = allRemoveImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(bottom = 5.dp, end = 5.dp)
                .size(200.dp)
        )
    }
}