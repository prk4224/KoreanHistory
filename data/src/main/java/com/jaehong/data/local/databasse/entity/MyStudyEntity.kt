package com.jaehong.data.local.databasse.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jaehong.domain.local.model.StudyInfoItem

@Entity(tableName = "MY_STUDY_TABLE")
data class MyStudyEntity (
    @PrimaryKey
    val id: String,
    val detail: String,
    val king_name: String,
    val description: String,
) {
    fun mappingDataFromDomain(): StudyInfoItem {
        return StudyInfoItem(this.id.split("/").first(), this.detail, this.king_name, arrayListOf(this.description))
    }
}