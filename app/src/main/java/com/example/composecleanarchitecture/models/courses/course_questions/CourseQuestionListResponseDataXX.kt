package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseDataXX(
    @SerializedName("attributes")
    val attributes: CourseQuestionListResponseAttributesX? = CourseQuestionListResponseAttributesX(),
    @SerializedName("id")
    val id: String? = ""
)