package com.jaehong.presenter.ui.modern_after

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
import com.jaehong.presenter.R
import com.jaehong.presenter.theme.Gray1
import com.jaehong.presenter.ui.MainActivity
import com.jaehong.domain.local.model.enum_type.DynastyType

@Composable
fun TypeCheckButton(
    dynastyType: String,
    animationHeight: Int,
    animationRadius: Int,
    logo: Painter = painterResource(id = R.drawable.woo_su_mark),
    typeCheckButtonItem: @Composable (String) -> Unit,
    typeCheckTitleItem: @Composable (String,Int) -> Unit,
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
            horizontalAlignment = Alignment.Start,
        ) {

            val list = when(dynastyType) {
                DynastyType.MODERN.value -> {
                    MainActivity.modernList
                }
                DynastyType.JAPANESE.value -> {
                    MainActivity.japaneseList
                }
                DynastyType.CONTEMPORARY.value -> {
                    MainActivity.contemporaryList
                }
                else -> {
                    throw IllegalArgumentException("Dynasty Type Error (Modern After)")
                }
            }

            list.forEachIndexed { index, data ->
                typeCheckButtonItem("${index+1}.$data")
            }
        }
        typeCheckTitleItem(dynastyType, animationHeight)
        logoImage(logo)
    }
}