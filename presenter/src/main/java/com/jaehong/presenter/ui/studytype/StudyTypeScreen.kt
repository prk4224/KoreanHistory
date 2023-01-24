package com.jaehong.presenter.ui.studytype

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.util.composable.LogoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun StudyTypeScreen(
    studyTypeViewModel: StudyTypeViewModel = hiltViewModel()
) {
    val dynastyType = studyTypeViewModel.dynastyState.collectAsState().value
    val isVisible = studyTypeViewModel.isVisible.collectAsState().value

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

    StudyTypeButton(
        dynastyType = dynastyType,
        animationHeight = animationHeight,
        animationRadius = animationRadius,
        studyTypeButtonItem = {
            StudyTypeButtonItem(
                dynastyType = dynastyType,
                studyType = it,
                toStudyPageClicked = { dynasty, studyType ->
                    studyTypeViewModel.onNavigateToStudyPageClicked(dynasty,studyType)
                }
            )
        },
        studyTypeTitleItem = { dynasty, height ->
            StudyTypeTitleItem(dynasty,height)
        },
        logoImage = { LogoImage(it) }
    )

    studyTypeViewModel.startAnimation()
}