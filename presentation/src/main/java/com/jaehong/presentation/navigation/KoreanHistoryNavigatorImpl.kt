package com.jaehong.presentation.navigation

import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.channels.Channel
import javax.inject.Inject

class KoreanHistoryNavigatorImpl @Inject constructor() : KoreanHistoryNavigator {

    override val navigationChannel = Channel<NavigationIntent>(
        capacity = Int.MAX_VALUE,
        onBufferOverflow = BufferOverflow.DROP_LATEST,
    )

    override suspend fun navigateBack(
        route: String?,
        inclusive: Boolean,
    ) {
        navigationChannel.send(
            NavigationIntent.NavigateBack(
                route = route,
                inclusive = inclusive
            )
        )
    }

    override suspend fun navigateTo(
        route: String,
        popUpToRoute: String?,
        inclusive: Boolean,
        isSingleTop: Boolean,
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
}