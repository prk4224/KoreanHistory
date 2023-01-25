package com.jaehong.presenter.ui.dynasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.jaehong.domain.local.model.enum_type.DynastyType
import com.jaehong.presenter.R
import com.jaehong.presenter.ui.MainActivity

@Composable
fun DynastyButton(
    isVisible: Boolean,
    markImage: Painter = painterResource(id = R.drawable.woo_su_mark),
    logoImage: @Composable (Painter) -> Unit,
    dynastyButtonItem: @Composable (String,Boolean) -> Unit
) {
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White),
    ) {
        LazyColumn(
            modifier = Modifier
                .align(Alignment.Center)
                .height(300.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(MainActivity.dynastyList) {
                dynastyButtonItem(it.value,isVisible)
            }
        }
        logoImage(markImage)
    }
}