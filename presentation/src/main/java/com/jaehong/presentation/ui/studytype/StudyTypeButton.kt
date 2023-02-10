package com.jaehong.presentation.ui.studytype

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.R
import com.jaehong.presentation.theme.Gray1
import com.jaehong.presentation.ui.MainActivity

@Composable
fun StudyTypeButton(
    dynastyType: String,
    animationHeight: Int,
    animationRadius: Int,
    logo: Painter = painterResource(id = R.drawable.woo_su_mark),
    studyTypeButtonItem: @Composable (String) -> Unit,
    studyTypeTitleItem: @Composable (String,Int) -> Unit,
    logoImage: @Composable (Painter) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Gray1),
        contentAlignment = Alignment.Center,
    ){
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp)
                .height(animationHeight.dp)
                .background(Color.White, RoundedCornerShape(animationRadius)),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            MainActivity.studyList.forEach {
                studyTypeButtonItem(it.value)
            }
        }
        studyTypeTitleItem(dynastyType, animationHeight)
        logoImage(logo)
    }
}