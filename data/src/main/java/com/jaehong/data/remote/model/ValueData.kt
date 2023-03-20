package com.jaehong.data.remote.model

import com.google.gson.annotations.SerializedName

sealed class ValueData {
    data class ArrayValue(
        @SerializedName("arrayValue")
        val value: MapArrayRootValues
    ): ValueData()

    data class StringArrayValue(
        @SerializedName("arrayValue")
        val value: StringArrayRootValues
    )

    data class StringValue(
        @SerializedName("stringValue")
        val value: String
    ): ValueData()

    data class MapValue(
        @SerializedName("mapValue")
        val value: MapValueRootField
    ): ValueData()
}