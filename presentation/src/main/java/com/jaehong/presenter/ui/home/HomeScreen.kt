package com.jaehong.presenter.ui.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jaehong.presenter.navigation.Destination
import com.jaehong.presenter.navigation.NavHost
import com.jaehong.presenter.navigation.NavigationIntent
import com.jaehong.presenter.navigation.composable
import com.jaehong.presenter.theme.KoreanHistoryTheme
import com.jaehong.presenter.ui.dynasty.DynastyScreen
import com.jaehong.presenter.ui.modern_after.TypeCheckScreen
import com.jaehong.presenter.ui.mystudy.MyStudyScreen
import com.jaehong.presenter.ui.studypage.StudyPageScreen
import com.jaehong.presenter.ui.studytype.StudyTypeScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val navController = rememberNavController()
    NavigationEffects(
        navigationChannel = homeViewModel.navigationChannel,
        navHostController = navController
    )
    KoreanHistoryTheme {
        NavHost(
            navController = navController,
            startDestination = Destination.Dynasty) {
            composable(Destination.Dynasty) {
                DynastyScreen()
            }
            composable(Destination.MyStudy) {
                MyStudyScreen()
            }
            composable(Destination.StudyType) {
                StudyTypeScreen()
            }
            composable(Destination.StudyPage) {
                StudyPageScreen()
            }
            composable(Destination.TypeCheck) {
                TypeCheckScreen()
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }

            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }
                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}