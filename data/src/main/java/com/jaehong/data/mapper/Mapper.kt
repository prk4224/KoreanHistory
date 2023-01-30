package com.jaehong.data.mapper

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.model.StudyEntity
import com.jaehong.data.local.model.StudyEntityItem
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.data.util.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.enum_type.DynastyType

object Mapper {

    fun StudyEntity.dataToDomain(): StudyInfo {
        val studyInfo = StudyInfo()
        this.forEach {
            val index = studyInfo.checkDescription(it.detail,it.king_name)
            if (index != -1) {
                studyInfo[index].description.add(it.description)
            } else {
                studyInfo.add(it.dataToDomain())
            }
        }
        return studyInfo
    }

    fun StudyEntityItem.dataToDomain(): StudyInfoItem {
        return StudyInfoItem(this.id, this.detail, this.king_name, arrayListOf(this.description))
    }

    private fun StudyInfo.checkDescription(detail: String,kingName: String): Int {
        this.forEachIndexed { index, studyInfoItem ->
            if (studyInfoItem.detail == detail && studyInfoItem.king_name == kingName) {
                return index
            }
        }
        return -1
    }

    fun List<MyStudyEntity>.dataBaseToDomain(): StudyInfo {
        val studyInfo = StudyInfo()
        this.forEach {
            val index = studyInfo.checkDescription(it.detail,it.king_name)
            if (index != -1) {
                studyInfo[index].description.add(it.description)
            } else {
                studyInfo.add(it.dataBaseToDomain())
            }
        }
        return studyInfo
    }

    fun List<StudyInfoItem>.domainToDataBase(): List<MyStudyEntity> {
        val studyInfo = mutableListOf<MyStudyEntity>()
        this.forEach {
            studyInfo.addAll(it.domainToDataBase())
        }
        return studyInfo
    }

    fun MyStudyEntity.dataBaseToDomain(): StudyInfoItem {
        return StudyInfoItem(this.id, this.detail, this.king_name, arrayListOf(this.description))
    }

    fun StudyInfoItem.domainToDataBase(): List<MyStudyEntity> {
        val myStudyItems = mutableListOf<MyStudyEntity>()
        this.description.forEachIndexed { index, data ->
            val temp = MyStudyEntity(this.id + index, this.detail, this.king_name, data)
            myStudyItems.add(temp)
        }

        return myStudyItems
    }

    fun String.checkedType(): DynastyDetailType {
        return when(this) {
            DynastyType.SAM_GUG.value -> DynastyDetailType.SAM_GUG
            DynastyType.SIN_LA.value -> DynastyDetailType.SIN_LA
            DynastyType.GO_LYEO.value -> DynastyDetailType.GO_LYEO
            DynastyType.JO_SEON.value -> DynastyDetailType.JO_SEON
            DynastyType.MODERN.combineValue(1) -> DynastyDetailType.MODERN_ONE
            DynastyType.MODERN.combineValue(2) -> DynastyDetailType.MODERN_TWO
            DynastyType.MODERN.combineValue(3) -> DynastyDetailType.MODERN_THREE
            DynastyType.MODERN.combineValue(4) -> DynastyDetailType.MODERN_FOUR
            DynastyType.JAPANESE.combineValue(1) -> DynastyDetailType.JAPANESE_ONE
            DynastyType.JAPANESE.combineValue(2) -> DynastyDetailType.JAPANESE_TWO
            DynastyType.JAPANESE.combineValue(3) -> DynastyDetailType.JAPANESE_THREE
            DynastyType.CONTEMPORARY.combineValue(1) -> DynastyDetailType.CONTEMPORARY_ONE
            DynastyType.CONTEMPORARY.combineValue(2) -> DynastyDetailType.CONTEMPORARY_TWO
            DynastyType.CONTEMPORARY.combineValue(3) -> DynastyDetailType.CONTEMPORARY_THREE
            else -> throw IllegalArgumentException("Dynasty Type Error")
        }
    }
}