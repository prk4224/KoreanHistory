package com.jaehong.data.mapper

import com.jaehong.data.local.databasse.entity.MyStudyEntity
import com.jaehong.data.local.databasse.entity.StudyInfoEntity
import com.jaehong.data.remote.model.RootField
import com.jaehong.data.util.Constants
import com.jaehong.domain.local.model.StudyInfoItem
import com.jaehong.domain.local.model.enum_type.DynastyDetailType
import com.jaehong.domain.local.model.enum_type.DynastyType
import com.jaehong.domain.local.model.result.DbResult
import com.jaehong.domain.local.model.result.ApiResult

object Mapper {

    fun List<StudyInfoEntity>.dataFromDomain(): List<StudyInfoItem> {
        return this.map { it.mappingDataFromDomain() }
    }

    fun List<StudyInfoItem>.domainFromData(studyType: String): List<StudyInfoEntity> {
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

    fun List<MyStudyEntity>.mappingListDataFromDomain(): List<StudyInfoItem> {
        val studyInfo = arrayListOf<StudyInfoItem>()
        this.forEach {
            val index = studyInfo.checkDescription(it.detail, it.king_name)
            if (index != -1) {
                studyInfo[index].description.add(it.description)
            } else {
                studyInfo.add(it.mappingDataFromDomain())
            }
        }
        return studyInfo
    }

    private fun List<StudyInfoItem>.checkDescription(detail: String, kingName: String): Int {
        this.forEachIndexed { index, studyInfoItem ->
            if (studyInfoItem.detail == detail &&
                studyInfoItem.king_name == kingName &&
                studyInfoItem.king_name != Constants.NOTHING_TEXT
            ) {
                return index
            }
        }
        return -1
    }

    fun ApiResult<RootField>.dataFromDomain(): ApiResult<List<StudyInfoItem>> {
        return when(this) {
            is ApiResult.Success -> {
                ApiResult.Success(
                    this.data.mappingDataFromDomain()
                )
            }
            is ApiResult.Error -> {
                this
            }
            else -> throw Exception("")
        }
    }

    fun DbResult<List<StudyInfoEntity>>.dataFromDomain(): DbResult<List<StudyInfoItem>> {
        return when(this) {
            is DbResult.Success -> {
                DbResult.Success(this.data.dataFromDomain())
            }
            is DbResult.Error -> {
                this
            }
            else -> throw Exception("")
        }
    }

    fun DbResult<List<MyStudyEntity>>.mappingListDataFromDomain(): DbResult<List<StudyInfoItem>> {
        return when(this) {
            is DbResult.Success -> {
                DbResult.Success(this.data.mappingListDataFromDomain())
            }
            is DbResult.Error -> {
                this
            }
            else -> throw Exception("")
        }
    }



    fun List<StudyInfoItem>.domainFromDataBase(): List<MyStudyEntity> {
        val myStudyInfo = mutableListOf<MyStudyEntity>()
        this.forEach {
            myStudyInfo.addAll(it.domainFromDataBase())
        }
        return myStudyInfo
    }

    fun StudyInfoItem.domainFromDataBase(): List<MyStudyEntity> {
        val myStudyItems = mutableListOf<MyStudyEntity>()
        this.description.forEach { desc ->
            val temp = MyStudyEntity(this.id+"/$desc", this.detail, this.king_name, desc)
            myStudyItems.add(temp)
        }

        return myStudyItems
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

    fun<T> List<T>.listFromArrayList(): ArrayList<T> {
        return ArrayList(this)
    }
}