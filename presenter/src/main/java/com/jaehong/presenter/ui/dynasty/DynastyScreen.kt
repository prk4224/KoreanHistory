package com.jaehong.presenter.ui.dynasty

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
    val markImage = painterResource(id = R.drawable.woo_su_mark)
    val guideImage = painterResource(id = R.drawable.guide_image)

    DynastyButton(
        isVisible = isVisible,
        markImage = markImage,
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
            guideImage = guideImage,
            onGuideClicked = { dynastyViewModel.onDialogDismiss(it) }
        )
    }
} 