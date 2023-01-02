package com.jaehong.data.mapper

import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.local.model.StudyEntityItem
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

object Mapper {

    fun StudyEntity.toDomain(): StudyInfo{
        val uiStudyInfo = StudyInfo()
        this.forEach {
            uiStudyInfo.add(it.toDmain())
        }
        return uiStudyInfo
    }

    fun StudyEntityItem.toDmain(): StudyInfoItem{
        return StudyInfoItem(this.dynasty,this.title,this.description)
    }
}