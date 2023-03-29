package com.jaehong.domain.local.model

data class StudyInfoItem(
    val id: String = "",
    val detail: String,
    var king_name: String,
    var description: ArrayList<String>,
)