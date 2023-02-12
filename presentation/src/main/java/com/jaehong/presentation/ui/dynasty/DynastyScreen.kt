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
    val isVisible by dynastyViewModel.isVisible.collectAsState()
    val dialogState by dynastyViewModel.showDialog.collectAsState()
    val (checked,setChecked) = remember { mutableStateOf(false) }

    DynastyButton(
        isVisible = isVisible,
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

    if(dialogState){
        GuideDialogContent(
            onGuideClicked = { dynastyViewModel.onDialogDismiss(it) },
            checked = checked,
            onCheckChange = setChecked
        )
    }
}