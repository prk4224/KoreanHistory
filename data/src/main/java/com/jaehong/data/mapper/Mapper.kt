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
            val index = studyInfo.checkDescription(it.king_name)
            if(index != -1){
                studyInfo[index].description.add(it.description)
            } else {
                studyInfo.add(it.dataToDomain())
            }
        }
        return studyInfo
    }

    fun StudyEntityItem.dataToDomain(): StudyInfoItem{
        return StudyInfoItem(this.id,this.detail,this.king_name, arrayListOf(this.description))
    }

    private fun StudyInfo.checkDescription(kingName: String): Int{
        this.forEachIndexed { index, studyInfoItem ->
            if(studyInfoItem.king_name == kingName){
                return index
            }
        }
        return -1
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
        return StudyInfoItem(this.id,this.detail,this.king_name, arrayListOf(this.description))
    }

    fun StudyInfoItem.domainToDataBase(): MyStudyEntity{
        if(this.description.size > 1){
            throw IllegalArgumentException("Index Error")
        }
        return MyStudyEntity(this.id,this.detail,this.king_name,this.description[0])
    }
}