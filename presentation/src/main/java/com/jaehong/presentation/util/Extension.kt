package com.jaehong.presentation.util

import com.jaehong.domain.local.model.enum_type.DynastyType

object Extension {

    fun String.checkedType(): Boolean {
        return this.contains(DynastyType.MODERN.value) ||
                this.contains(DynastyType.JAPANESE.value) ||
                this.contains(DynastyType.CONTEMPORARY.value)
    }
}