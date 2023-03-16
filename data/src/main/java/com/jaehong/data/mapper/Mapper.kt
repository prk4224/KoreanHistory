package com.jaehong.data.mapper

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity
import com.jaehong.data.remote.model.StudyEntity
import com.jaehong.data.remote.model.StudyEntityItem
import com.jaehong.data.util.Constants.NOTHING_TEXT
import com.jaehong.domain.local.model.StudyInfo
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.enum_type.DynastyType

object Mapper {

    fun List<StudyInfoEntity>.dataToDomain(): List<StudyInfoItem> {
        return this.map {
            StudyInfoItem(
                it.id,
                it.detail,
                it.king_name,
                it.description.changeTypeListToArrayList()
            )
        }
    }

    fun List<StudyInfoItem>.domainToData(studyType: String): List<StudyInfoEntity> {
        return this.map {
            StudyInfoEntity(
                it.id,
                it.detail,
                it.king_name,
                it.description,
                studyType
            )
        }
    }

    fun StudyEntity.dataToDomain(): StudyInfo {
        val temp = StudyInfo(arrayListOf())
        temp.items.addAll(this.items.map { it.dataToDomain() })

        return temp
    }

    fun StudyEntityItem.dataToDomain(): StudyInfoItem {
        return StudyInfoItem(
            this.id,
            this.detail,
            this.king_name,
            this.description.changeTypeListToArrayList()
        )
    }


    fun List<MyStudyEntity>.dataBaseToDomain(): StudyInfo {
        val studyInfo = StudyInfo(arrayListOf())
        this.forEach {
            val index = studyInfo.checkDescription(it.detail, it.king_name)
            if (index != -1) {
                studyInfo.items[index].description.add(it.description)
            } else {
                studyInfo.items.add(it.dataBaseToDomain())
            }
        }
        return studyInfo
    }

    private fun StudyInfo.checkDescription(detail: String, kingName: String): Int {
        this.items.forEachIndexed { index, studyInfoItem ->
            if (studyInfoItem.detail == detail &&
                studyInfoItem.king_name == kingName &&
                studyInfoItem.king_name != NOTHING_TEXT
            ) {
                return index
            }
        }
        return -1
    }

    fun List<StudyInfoItem>.domainToDataBase(): List<MyStudyEntity> {
        val myStudyInfo = mutableListOf<MyStudyEntity>()
        this.forEach {
            myStudyInfo.add(it.domainToDataBase())
        }
        return myStudyInfo
    }

    fun MyStudyEntity.dataBaseToDomain(): StudyInfoItem {
        return StudyInfoItem(this.id, this.detail, this.king_name, arrayListOf(this.description))
    }

    fun StudyInfoItem.domainToDataBase(): MyStudyEntity {
        return MyStudyEntity(this.id, this.detail, this.king_name, this.description[0])
    }

    fun String.checkedType(): DynastyDetailType {
        return when (this) {
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

    private fun <T> List<T>.changeTypeListToArrayList(): ArrayList<T> {
        val arrayList = arrayListOf<T>()
        this.forEach {
            arrayList.add(it)
        }
        return arrayList
    }
}