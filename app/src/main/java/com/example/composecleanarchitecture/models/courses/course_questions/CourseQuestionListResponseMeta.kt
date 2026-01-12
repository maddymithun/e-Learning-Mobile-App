package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseMeta(
    @SerializedName("pagination")
    val pagination: CourseQuestionListResponsePagination? = CourseQuestionListResponsePagination()
)