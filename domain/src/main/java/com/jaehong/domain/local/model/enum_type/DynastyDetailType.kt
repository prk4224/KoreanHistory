package com.jaehong.domain.local.model.enum_type

import com.jaehong.domain.local.model.util.Constant.STUDY_TYPE_ALL
import com.jaehong.domain.local.model.util.Constant.STUDY_TYPE_FIRST

enum class DynastyDetailType(
    val value:(String) -> String,
    ) {
    SAM_GUG({
        when(it) {
            STUDY_TYPE_ALL -> "samkok_all"
            STUDY_TYPE_FIRST -> "samkok_first_letter"
            else -> ""
        }
    }),
    SIN_LA({
        when(it) {
            STUDY_TYPE_ALL -> "hoosamkok_all"
            STUDY_TYPE_FIRST -> "hoosamkok_first_letter"
            else -> ""
        }
    }),
    GO_LYEO({
        when(it) {
            STUDY_TYPE_ALL -> "golyeo_all"
            STUDY_TYPE_FIRST -> "golyeo_first_letter"
            else -> ""
        }
    }),
    JO_SEON({
        when(it) {
            STUDY_TYPE_ALL -> "joseon_all"
            STUDY_TYPE_FIRST -> "joseon_first_letter"
            else -> ""
        }
    }),
    MODERN_ONE({
        when(it) {
            STUDY_TYPE_ALL -> "modern_all_1"
            STUDY_TYPE_FIRST -> "modern_first_letter_1"
            else -> ""
        }
    }),
    MODERN_TWO({
        when(it) {
            STUDY_TYPE_ALL -> "modern_all_2"
            STUDY_TYPE_FIRST -> "modern_first_letter_2"
            else -> ""
        }
    }),
    MODERN_THREE({
        when(it) {
            STUDY_TYPE_ALL -> "modern_all_3"
            STUDY_TYPE_FIRST -> "modern_first_letter_3"
            else -> ""
        }
    }),
    MODERN_FOUR({
        when(it) {
            STUDY_TYPE_ALL -> "modern_all_4"
            STUDY_TYPE_FIRST -> "modern_first_letter_4"
            else -> ""
        }
    }),
    JAPANESE_ONE({
        when(it) {
            STUDY_TYPE_ALL -> "japanese_all_1"
            STUDY_TYPE_FIRST -> "japanese_first_letter_1"
            else -> ""
        }
    }),
    JAPANESE_TWO({
        when(it) {
            STUDY_TYPE_ALL -> "japanese_all_2"
            STUDY_TYPE_FIRST -> "japanese_first_letter_2"
            else -> ""
        }
    }),
    JAPANESE_THREE({
        when(it) {
            STUDY_TYPE_ALL -> "japanese_all_3"
            STUDY_TYPE_FIRST -> "japanese_first_letter_3"
            else -> ""
        }
    }),
    CONTEMPORARY_ONE({
        when(it) {
            STUDY_TYPE_ALL -> "contemporary_all_1"
            STUDY_TYPE_FIRST -> "contemporary_first_letter_1"
            else -> ""
        }
    }),
    CONTEMPORARY_TWO({
        when(it) {
            STUDY_TYPE_ALL -> "contemporary_all_2"
            STUDY_TYPE_FIRST -> "contemporary_first_letter_2"
            else -> ""
        }
    }),
    CONTEMPORARY_THREE({
        when(it) {
            STUDY_TYPE_ALL -> "contemporary_all_3"
            STUDY_TYPE_FIRST -> "contemporary_first_letter_3"
            else -> ""
        }
    });
}
