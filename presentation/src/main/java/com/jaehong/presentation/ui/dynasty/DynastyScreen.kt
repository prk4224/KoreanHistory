package com.jaehong.presentation.ui.dynasty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presentation.util.composable.LogoImage
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@Composable
fun DynastyScreen(
    dynastyViewModel: DynastyViewModel = hiltViewModel()
) {
    val isVisible = dynastyViewModel.isVisible.collectAsState().value
    val dialogState = dynastyViewModel.showDialog.collectAsState().value
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