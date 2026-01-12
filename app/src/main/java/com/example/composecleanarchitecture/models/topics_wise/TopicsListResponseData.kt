package com.example.composecleanarchitecture.models.topics_wise


import com.google.gson.annotations.SerializedName

data class TopicsListResponseData(
    @SerializedName("topics")
    val topics: TopicsListResponseTopics? = TopicsListResponseTopics(),

)
data class SubTopicsListResponseData(
    @SerializedName("subTopics")
    val subTopics: SubTopicsListResponseDataX?,

)
data class SubjectListResponseData(
    @SerializedName("subjects")
    val subjects: TSubjectListListResponseSubject? = TSubjectListListResponseSubject()
)
