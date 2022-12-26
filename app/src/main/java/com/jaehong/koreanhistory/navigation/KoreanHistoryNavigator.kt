package com.jaehong.koreanhistory.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel

class KoreanHistoryNavigator {

    val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    suspend fun navigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    ) {
        navigationChannel.send(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive
            )
        )
    }

    fun tryNavigateBack(
        route: String? = null,
        inclusive: Boolean = false,
    ) {
        navigationChannel.trySend(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive
            )
        )
    }

    suspend fun navigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    ) {
        navigationChannel.send(
            NavigationIntent.NavigateTo(
                route = route,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop,
            )
        )
    }

    fun tryNavigateTo(
        route: String,
        popUpToRoute: String? = null,
        inclusive: Boolean = false,
        isSingleTop: Boolean = false,
    ) {
        navigationChannel.trySend(
            NavigationIntent.NavigateTo(
                route = route,
                popUpToRoute = popUpToRoute,
                inclusive = inclusive,
                isSingleTop = isSingleTop,
            )
        )
    }
}
sealed class NavigationIntent {
    data class NavigateBack(
        val route: String? = null,
        val inclusive: Boolean = false,
    ) : NavigationIntent()

    data class NavigateTo(
        val route: String,
        val popUpToRoute: String? = null,
        val inclusive: Boolean = false,
        val isSingleTop: Boolean = false,
    ) : NavigationIntent()
}