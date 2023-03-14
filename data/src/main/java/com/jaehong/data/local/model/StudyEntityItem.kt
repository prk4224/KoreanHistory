package com.jaehong.data.local.model

data class StudyEntityItem(
    val id: Int = 0,
    val detail: String = "",
    val king_name: String = "",
    val description: ArrayList<String> = arrayListOf(),
)