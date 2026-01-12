package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponse(
    @SerializedName("data")
    val `data`: CourseQuestionListResponseData? = CourseQuestionListResponseData()
)