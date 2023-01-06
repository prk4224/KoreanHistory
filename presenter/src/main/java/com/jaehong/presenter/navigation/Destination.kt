package com.jaehong.presenter.navigation

import com.jaehong.presenter.navigation.DestinationConstants.DYNASTY
import com.jaehong.presenter.navigation.DestinationConstants.MY_STUDY
import com.jaehong.presenter.navigation.DestinationConstants.STUDY_PAGE
import com.jaehong.presenter.navigation.DestinationConstants.STUDY_TYPE

sealed class Destination(protected val route: String, vararg params: String) {

    val fullRoute: String = if (params.isEmpty()) route else {
        val builder = StringBuilder(route)
        params.forEach { builder.append("/{${it}}") }
        builder.toString()
    }

    sealed class NoArgumentsDestination(route: String) : Destination(route) {
        operator fun invoke(): String = route
    }

    object Dynasty : NoArgumentsDestination(DYNASTY)
    object MyStudy : NoArgumentsDestination(MY_STUDY)
    object StudyType : Destination(STUDY_TYPE, "dynastyType") {
        const val DYNASTY_TYPE_KEY = "dynastyType"

        operator fun invoke(dynastyType: String): String = route.appendParams(
            DYNASTY_TYPE_KEY to dynastyType
        )
    }
    object StudyPage : Destination(STUDY_PAGE, "dynastyType", "studyType") {
        const val DYNASTY_TYPE_KEY = "dynastyType"
        const val STUDY_TYPE_KEY = "studyType"

        operator fun invoke(dynastyType: String, studyType: String): String = route.appendParams(
            DYNASTY_TYPE_KEY to dynastyType,
            STUDY_TYPE_KEY to studyType
        )
    }


}

private fun String.appendParams(vararg params: Pair<String, Any?>): String {
    val builder = StringBuilder(this)

    params.forEach {
        it.second?.toString()?.let { arg ->
            builder.append("/$arg")
        }
    }

    return builder.toString()
}
