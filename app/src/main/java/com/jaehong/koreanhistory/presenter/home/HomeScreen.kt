package com.jaehong.koreanhistory.presenter.home

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.jaehong.koreanhistory.navigation.Destination
import com.jaehong.koreanhistory.navigation.NavHost
import com.jaehong.koreanhistory.navigation.NavigationIntent
import com.jaehong.koreanhistory.navigation.composable
import com.jaehong.koreanhistory.presenter.studytype.StudyTypeScreen
import com.jaehong.koreanhistory.presenter.dynasty.DynastyScreen
import com.jaehong.koreanhistory.ui.theme.KoreanHistoryTheme
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
        // A surface container using the 'background' color from the theme
        NavHost(
            navController = navController,
            startDestination = Destination.Dynasty) {
            composable(Destination.Dynasty) {
                DynastyScreen()
            }
            composable(Destination.StudyType) {
                StudyTypeScreen()
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