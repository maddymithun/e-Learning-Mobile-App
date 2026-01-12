package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseExamQuestions(
    @SerializedName("data")
    val `data`: List<CourseQuestionListResponseDataX>? = listOf(),
    @SerializedName("meta")
    val meta: CourseQuestionListResponseMeta? = CourseQuestionListResponseMeta()
)