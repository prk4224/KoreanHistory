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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presenter.R
import com.jaehong.presenter.util.Constants.ORIGIN_STUDY

@Composable
fun ScrollRuleOriginDialog(
    onClickUpdate: (Int,String) -> Unit,
) {
    val scrollImage = painterResource(id = R.drawable.scroll_image)

    Box(modifier = Modifier
        .fillMaxSize()
        .clickable(
            onClick = {
                onClickUpdate(2,ORIGIN_STUDY)
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