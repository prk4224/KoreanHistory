package com.jaehong.presenter.ui.modern_after

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.util.composable.LogoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun TypeCheckScreen(
    typeCheckViewModel: TypeCheckViewModel = hiltViewModel()
) {

    val dynastyType = typeCheckViewModel.dynastyState.collectAsState().value
    val isVisible = typeCheckViewModel.isVisible.collectAsState().value

    val animationHeight by animateIntAsState(
        targetValue = if(isVisible) 400 else 50,
        animationSpec = tween(
            delayMillis = 100
        )
    )
    val animationRadius by animateIntAsState(
        targetValue = if(isVisible) 10 else 50,
        animationSpec = tween(
            delayMillis = 100
        )
    )

    TypeCheckButton(
        dynastyType = dynastyType,
        animationHeight = animationHeight,
        animationRadius = animationRadius,
        typeCheckButtonItem = {
            TypeCheckButtonItem(
                dynastyType = dynastyType,
                detailType = it,
                toStudyTypeClicked = { dynasty, detail ->
                    typeCheckViewModel.onNavigateToStudyTypeClicked("$dynasty\n$detail")
                }
            )
        },
        typeCheckTitleItem = { dynasty, height ->
            TypeCheckTitleItem(dynasty,height)
        },
        logoImage = { LogoImage(it) }
    )

    typeCheckViewModel.startAnimation()
}