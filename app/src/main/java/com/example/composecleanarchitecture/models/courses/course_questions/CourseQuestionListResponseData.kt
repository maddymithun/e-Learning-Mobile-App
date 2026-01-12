package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseData(
    @SerializedName("courseExamQuestions")
    val courseExamQuestions: CourseExamQuestions? = CourseExamQuestions()
)