package com.jaehong.data.local.databasse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "MY_STUDY_TABLE")
data class MyStudyEntity (
    @PrimaryKey
    val id: Int,
    val detail: String,
    val king_name: String,
    val description: String,
)