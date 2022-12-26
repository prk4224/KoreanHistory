package com.jaehong.koreanhistory.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.*
import androidx.navigation.compose.composable

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object Dynasty : NoArgumentsDestination(DestinationConstants.DYNASTY)
    object StudyType : Destination(DestinationConstants.STUDY_TYPE, "dynastyType") {
        const val DYNASTY_TYPE_KEY = "dynastyType"

        operator fun invoke(dynastyType: String): String = route.appendParams(
            DYNASTY_TYPE_KEY to dynastyType
        )
    }
    object StudyPage : Destination(DestinationConstants.STUDY_PAGE, "dynastyType", "studyType") {
        const val DYNASTY_TYPE_KEY = "dynastyType"
        const val STUDY_TYPE_KEY = "studyType"

        operator fun invoke(dynastyType: String, studyType: String): String = route.appendParams(
            DYNASTY_TYPE_KEY to dynastyType,
            STUDY_TYPE_KEY to studyType
        )
    }

}

internal fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}

@Composable
fun NavHost(
    navController: NavHostController,
    startDestination: Destination,
    modifier: Modifier = Modifier,
    route: String? = null,
    builder: NavGraphBuilder.() -> Unit
) {
    androidx.navigation.compose.NavHost(
        navController = navController,
        startDestination = startDestination.fullRoute,
        modifier = modifier,
        route = route,
        builder = builder
    )
}

fun NavGraphBuilder.composable(
    destination: Destination,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = destination.fullRoute,
        arguments = arguments,
        deepLinks = deepLinks,
        content = content
    )
}