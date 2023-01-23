package com.jaehong.presenter.ui.studypage.guide.origin

import androidx.compose.foundation.Image
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
import com.jaehong.presenter.R
import com.jaehong.presenter.util.enum.StudyType

@Composable
fun ScrollRuleOriginDialog(
    scrollImage: Painter = painterResource(id = R.drawable.scroll_image),
    onClickUpdate: (Int,String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(2,StudyType.ORIGIN_STUDY.value)
            }
        )
    ) {
        Image(
            painter = scrollImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 50.dp)
                .size(200.dp)
        )
    }
}