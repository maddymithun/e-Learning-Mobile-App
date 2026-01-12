package com.example.composecleanarchitecture.models.topics_wise.sub_topics


import com.example.composecleanarchitecture.models.topics_wise.SubTopicsListResponseDataX
import com.google.gson.annotations.SerializedName

data class SubTopics(
    @SerializedName("data")
    val `data`: List<SubTopicsListResponseDataX>? = listOf(),
    @SerializedName("meta")
    val meta: Meta? = Meta()
)