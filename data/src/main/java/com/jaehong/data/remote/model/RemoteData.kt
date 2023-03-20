package com.jaehong.data.remote.model

import com.google.gson.annotations.SerializedName

sealed class RemoteData {

    data class StudyRemoteItems(
        @SerializedName("items")
        val items: ValueData.ArrayValue
    ) : RemoteData()

    data class StudyRemoteItem(
        @SerializedName("id")
        val id: ValueData.StringValue,

        @SerializedName("detail")
        val detail: ValueData.StringValue,

        @SerializedName("king_name")
        val kingName: ValueData.StringValue,

        @SerializedName("description")
        val description: ValueData.StringArrayValue,
    ) : RemoteData()

}