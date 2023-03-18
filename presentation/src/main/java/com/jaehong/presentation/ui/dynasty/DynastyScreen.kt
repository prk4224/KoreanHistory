package com.jaehong.presentation.ui.dynasty

import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presentation.util.composable.LogoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DynastyScreen(
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    val animationState by dynastyViewModel.animationState.collectAsState()
    val isVisibleDialog by dynastyViewModel.isVisibleDialog.collectAsState()
    val (checked,setChecked) = remember { mutableStateOf(false) }

    DynastyButton(
        animationState = animationState,
        dynastyButtonItem = { title, visible ->
            DynastyButtonItem(
                title = title,
                visible = visible,
                toMyStudyClicked = {
                    dynastyViewModel.onNavigateToMyStudyClicked()
                },
                toStudyTypeClicked = {
                    with(dynastyViewModel) {
                        if(title.checkedModernAfter()) onNavigateToTypeCheckClicked(title.value)
                        else onNavigateToStudyTypeClicked(title.value)
                    }
                }
            )
        },
        logoImage = {
            LogoImage(it)
        }
    )

    dynastyViewModel.startAnimation()

    if(isVisibleDialog){
        GuideDialogContent(
            onGuideClicked = { dynastyViewModel.onDialogDismiss(it) },
            checked = checked,
            onCheckChange = setChecked
        )
    }
}