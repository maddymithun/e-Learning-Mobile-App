package com.example.composecleanarchitecture.models.topics_wise.sub_topics


import com.google.gson.annotations.SerializedName

data class SubjectSubtopicsData(
    @SerializedName("subTopics")
    val subTopics: SubTopics? = SubTopics()
)