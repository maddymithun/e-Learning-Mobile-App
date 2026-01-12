package com.example.composecleanarchitecture.models.subject_wise

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.composecleanarchitecture.models.topics_wise.TopicsListResponseAttributes
import com.google.gson.annotations.SerializedName

@Entity
data class SubjectListResponseDataX(
    @PrimaryKey(autoGenerate = true) val columnId: Int = 0,
    @SerializedName("attributes")
    @Embedded val attributes: TopicsListResponseAttributes?,
    @SerializedName("id")
    val id: String? = ""
)
