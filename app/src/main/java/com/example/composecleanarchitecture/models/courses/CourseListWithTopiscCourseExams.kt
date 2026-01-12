package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscCourseExams(
    @SerializedName("data")
    val `data`: List<CourseListWithTopiscDataXX>? = listOf()
)