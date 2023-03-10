package com.jaehong.presentation.ui.studypage.guide.blank

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.R
import com.jaehong.presentation.theme.BlackWithAlpha50
import com.jaehong.domain.local.model.enum_type.GuideKey
import com.jaehong.domain.local.model.enum_type.StudyType

@Composable
fun SelectRuleBlankDialog(
    checkboxBlankImage: Painter = painterResource(id = R.drawable.check_box_blank),
    onClickUpdate: (Int,String,String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(BlackWithAlpha50)
        .clickable(
            onClick = {
                onClickUpdate(
                    0,
                    StudyType.ALL_BLANK_REVIEW.value,
                    GuideKey.USER_RULE_BLANK.value
                )
            }
        )
    ) {
        Image(
            painter = checkboxBlankImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .fillMaxWidth()
                .size(165.dp)

        )
    }
}