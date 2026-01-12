package com.example.composecleanarchitecture.models.topics_wise


import com.google.gson.annotations.SerializedName

data class TopicsListResponse(
    @SerializedName("data")
    val `data`: TopicsListResponseData? = TopicsListResponseData()
)

data class SubTopicsListResponse(
    @SerializedName("data")
    val `data`: SubTopicsListResponseData?
)

data class SubjectListResponse(
    @SerializedName("data")
    val `data`: SubjectListResponseData? = SubjectListResponseData()
)