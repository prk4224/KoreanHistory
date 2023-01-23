package com.jaehong.presenter.ui.dynasty

import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.jaehong.presenter.R
import com.jaehong.presenter.util.composable.LogoImage

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
                    dynastyViewModel.onNavigateToStudyTypeClicked(title)
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