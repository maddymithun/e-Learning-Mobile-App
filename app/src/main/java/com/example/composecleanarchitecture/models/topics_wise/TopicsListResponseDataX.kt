package com.example.composecleanarchitecture.models.topics_wise


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class TopicsListResponseDataX(
    @PrimaryKey(autoGenerate = true) val columnId: Int = 0,
    val subjectCode: String = "",
    @SerializedName("attributes")
    @Embedded val attributes: TopicsListResponseAttributes?,
    @SerializedName("id")
    val id: String? = ""
)

@Entity
data class SubTopicsListResponseDataX(
    @PrimaryKey(autoGenerate = true) val columnId: Int = 0,
    val subjectCode: String = "",
    val topicsCode: String = "",
    @SerializedName("attributes")
    @Embedded val attributes: TopicsListResponseAttributes?,
    @SerializedName("id")
    val id: String? = ""
)