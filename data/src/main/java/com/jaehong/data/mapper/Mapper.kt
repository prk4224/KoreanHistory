package com.jaehong.data.mapper

import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.local.model.StudyEntityItem
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

object Mapper {

    fun StudyEntity.toDomain(): StudyInfo{
        val studyInfo = StudyInfo()
        this.forEach {
            studyInfo.add(it.toDomain())
        }
        return studyInfo
    }

    fun StudyEntityItem.toDomain(): StudyInfoItem{
        return StudyInfoItem(this.dynasty,this.title,this.description)
    }
}