package com.example.composecleanarchitecture.models.topics_wise


import androidx.room.ColumnInfo
import com.google.gson.annotations.SerializedName

data class TopicsListResponseAttributes(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @ColumnInfo(name = "post_code") val postCode: Int
)