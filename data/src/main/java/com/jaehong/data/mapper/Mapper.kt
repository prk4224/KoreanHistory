package com.jaehong.data.mapper

import com.jaehong.data.local.model.StudyInfo
import com.jaehong.data.local.model.StudyInfoItem
import com.jaehong.domain.local.model.UiStudyInfo
import com.jaehong.domain.local.model.UiStudyInfoItem

object Mapper {

    fun StudyInfo.changeDataToRepository(): UiStudyInfo{
        val uiStudyInfo = UiStudyInfo()
        this.forEach {
            uiStudyInfo.add(it.changeDataToRepository())
        }
        return uiStudyInfo
    }

    fun StudyInfoItem.changeDataToRepository(): UiStudyInfoItem{
        return UiStudyInfoItem(this.dynasty,this.title,this.description)
    }
}