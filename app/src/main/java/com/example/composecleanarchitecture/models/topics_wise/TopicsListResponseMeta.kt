package com.example.composecleanarchitecture.models.topics_wise


import com.google.gson.annotations.SerializedName

data class TopicsListResponseMeta(
    @SerializedName("pagination")
    val pagination: TopicsListResponsePagination? = TopicsListResponsePagination()
)