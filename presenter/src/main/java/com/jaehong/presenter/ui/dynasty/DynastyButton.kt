package com.jaehong.presenter.ui.dynasty

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
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
import com.jaehong.presenter.util.composable.verticalScrollbar

@Composable
fun DynastyButton(
    isVisible: Boolean,
    markImage: Painter = painterResource(id = R.drawable.woo_su_mark),
    listState: LazyListState = rememberLazyListState(),
    logoImage: @Composable (Painter) -> Unit,
    dynastyButtonItem: @Composable (DynastyType, Boolean) -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
    ) {
        LazyColumn(
            state = listState,
            modifier = Modifier
                .align(Alignment.Center)
                .height(300.dp)
                .fillMaxWidth()
                .verticalScrollbar(
                    state = listState,
                    alphaState = false),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(MainActivity.dynastyList) {
                dynastyButtonItem(it, isVisible)
            }
        }
    }
    logoImage(markImage)
}

