package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscAttributes(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("course_exams")
    val courseExams: CourseListWithTopiscCourseExams? = CourseListWithTopiscCourseExams(),
    @SerializedName("name")
    val name: String? = ""
)