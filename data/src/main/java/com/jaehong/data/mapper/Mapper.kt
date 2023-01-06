package com.jaehong.data.mapper

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.local.model.StudyEntityItem
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem

object Mapper {

    fun StudyEntity.dataToDomain(): StudyInfo{
        val studyInfo = StudyInfo()
        this.forEach {
            studyInfo.add(it.dataToDomain())
        }
        return studyInfo
    }

    fun List<MyStudyEntity>.dataBaseToDomain(): StudyInfo{
        val studyInfo = StudyInfo()
        this.forEach {
            studyInfo.add(it.dataBaseToDomain())
        }
        return studyInfo
    }

    fun List<StudyInfoItem>.domainToDataBase(): List<MyStudyEntity>{
        val studyInfo = mutableListOf<MyStudyEntity>()
        this.forEach {
            studyInfo.add(it.domainToDataBase())
        }
        return studyInfo
    }

    fun MyStudyEntity.dataBaseToDomain(): StudyInfoItem{
        return StudyInfoItem(this.id,this.detail,this.king_name,this.description)
    }

    fun StudyInfoItem.domainToDataBase(): MyStudyEntity{
        return MyStudyEntity(this.id,this.detail,this.king_name,this.description)
    }

    fun StudyEntityItem.dataToDomain(): StudyInfoItem{
        return StudyInfoItem(this.id,this.detail,this.king_name,this.description)
    }


}