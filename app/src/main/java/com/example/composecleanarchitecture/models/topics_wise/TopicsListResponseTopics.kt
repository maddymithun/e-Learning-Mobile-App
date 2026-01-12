package com.example.composecleanarchitecture.models.topics_wise


import com.example.composecleanarchitecture.models.subject_wise.SubjectListResponseDataX
import com.google.gson.annotations.SerializedName

data class TopicsListResponseTopics(
    @SerializedName("data")
    val `data`: List<TopicsListResponseDataX>? = listOf(),
    @SerializedName("meta")
    val meta: TopicsListResponseMeta? = TopicsListResponseMeta()
)

data class TSubjectListListResponseSubject(
    @SerializedName("data")
    val `data`: List<SubjectListResponseDataX>? = listOf(),
    @SerializedName("meta")
    val meta: TopicsListResponseMeta? = TopicsListResponseMeta()
)