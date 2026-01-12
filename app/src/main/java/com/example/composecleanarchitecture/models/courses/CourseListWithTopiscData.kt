package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscData(
    @SerializedName("courses")
    val courses: CourseListWithTopiscCourses? = CourseListWithTopiscCourses()
)