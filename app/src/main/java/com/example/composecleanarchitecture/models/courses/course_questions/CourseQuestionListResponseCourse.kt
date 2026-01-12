package com.example.composecleanarchitecture.models.courses.course_questions


import com.google.gson.annotations.SerializedName

data class CourseQuestionListResponseCourse(
    @SerializedName("data")
    val `data`: CourseQuestionListResponseDataXX? = CourseQuestionListResponseDataXX()
)