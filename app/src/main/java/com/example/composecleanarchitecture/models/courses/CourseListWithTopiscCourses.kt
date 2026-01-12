package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscCourses(
    @SerializedName("data")
    val `data`: List<CourseListWithTopiscDataX>? = listOf(),
    @SerializedName("meta")
    val meta: CourseListWithTopiscMeta? = CourseListWithTopiscMeta()
)