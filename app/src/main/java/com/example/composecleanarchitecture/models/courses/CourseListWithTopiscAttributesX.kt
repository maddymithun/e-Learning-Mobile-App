package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscAttributesX(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("exam_date")
    val examDate: String? = "",
    @SerializedName("publishedAt")
    val publishedAt: String? = "",
    @SerializedName("syllabus")
    val syllabus: String? = ""
)