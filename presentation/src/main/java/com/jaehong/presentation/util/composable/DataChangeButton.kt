package com.jaehong.presentation.util.composable

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.RemoveCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jaehong.presentation.theme.BaseColor1
import com.jaehong.presentation.util.Constants.REMOVE_SNACKBAR_MESSAGE
import com.jaehong.presentation.util.Constants.SAVE_SNACKBAR_MESSAGE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DataChangeButton(
    iconType: Boolean,
    isVisible: Boolean,
    size: Int,
    snackBarState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    snackBarMessage: String = if (iconType) SAVE_SNACKBAR_MESSAGE else REMOVE_SNACKBAR_MESSAGE,
    onIconClicked: () -> Unit,
    onIconLongClicked: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        AnimatedVisibility(
            visible = isVisible,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp),
            enter = slideInHorizontally(initialOffsetX = {
                +it
            }),
            exit = slideOutHorizontally(targetOffsetX = {
                +it
            }),
        ) {
            Icon(imageVector = if (iconType) Icons.Filled.AddCircle else Icons.Filled.RemoveCircle,
                tint = BaseColor1,
                contentDescription = null,
                modifier = Modifier
                    .size(45.dp)
                    .background(Color.White, CircleShape)
                    .combinedClickable(
                        onClick = {
                            onIconClicked()
                            showSnackBarOneSecond(
                                snackBarState,
                                coroutineScope,
                                "$size 개 $snackBarMessage"
                            )
                        },
                        onLongClick = { onIconLongClicked() }
                    )
            )
        }
        SnackbarHost(hostState = snackBarState, modifier = Modifier.align(Alignment.BottomCenter))
    }
}

private fun showSnackBarOneSecond(
    snackBarState: SnackbarHostState,
    coroutineScope: CoroutineScope,
    message: String
) {
    coroutineScope.launch {
        val scope = coroutineScope.launch {
            snackBarState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Indefinite
            )
        }
        delay(1000L)
        scope.cancel()
    }
}