package com.jaehong.presenter.ui.studypage.guide.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.R
import com.jaehong.presenter.util.enum.StudyType

@Composable
fun SelectRuleFirstDialog(
    checkboxFirstImage: Painter = painterResource(id = R.drawable.checkbox_image_first),
    onClickUpdate: (Int,String) -> Unit,
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(1,StudyType.FIRST_REVIEW.value)
            }
        )
    ) {
        Image(
            painter = checkboxFirstImage,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 100.dp)
                .fillMaxWidth()
                .size(165.dp)

        )
    }
}