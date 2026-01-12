package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseAttributesX(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = ""
)