package com.jaehong.presenter.ui.studypage.guide.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.R
import com.jaehong.presenter.util.enum.GuideKey
import com.jaehong.presenter.util.enum.StudyType

@Composable
fun LongClickRuleFirstDialog(
    longClickImage: Painter = painterResource(id = R.drawable.long_click_image),
    onClickUpdate: (Int,String,String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(
                    2,
                    StudyType.FIRST_REVIEW.value,
                    GuideKey.USER_RULE_FIRST.value
                )
            }
        ),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = longClickImage,
            contentDescription = null,
            modifier = Modifier
                .size(200.dp)
        )
    }

}