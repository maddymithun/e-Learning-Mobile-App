package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseDataX(
    @SerializedName("attributes")
    val attributes: CourseQuestionListResponseAttributes? = CourseQuestionListResponseAttributes(),
    @SerializedName("id")
    val id: String? = ""
)