package com.jaehong.data.remote.model

import com.google.gson.annotations.SerializedName
import com.jaehong.data.mapper.Mapper.listFromArrayList
import com.jaehong.domain.local.model.StudyInfoItem

data class RootField(
    @SerializedName("fields")
    val remoteData: RemoteData.StudyRemoteItems
) {
    fun mappingDataFromDomain(): List<StudyInfoItem> {
        val dataItems = this.remoteData.items.value.values

        val studyInfoItems = List(dataItems.size) { idx ->

            val item = dataItems[idx].value.remoteData
            val description = item.description.value.values
            val descriptionItems = List(description.size) { descIdx ->
                description[descIdx].value
            }

            StudyInfoItem(
                id = item.id.value,
                detail = item.detail.value,
                king_name = item.kingName.value,
                description = descriptionItems.listFromArrayList()
            )
        }

        return studyInfoItems.listFromArrayList()
    }
}

data class MapValueRootField(
    @SerializedName("fields")
    val remoteData: RemoteData.StudyRemoteItem
)

data class MapArrayRootValues(
    @SerializedName("values")
    val values: List<ValueData.MapValue>
)

data class StringArrayRootValues(
    @SerializedName("values")
    val values: List<ValueData.StringValue>
)
