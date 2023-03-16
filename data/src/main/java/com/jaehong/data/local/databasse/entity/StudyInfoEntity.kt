package com.jaehong.data.local.databasse.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "STUDY_INFO_TABLE")
data class StudyInfoEntity (
    @PrimaryKey
    val id: String,
    val detail: String,
    val king_name: String,
    val description: List<String>,
    val pageType: String
)