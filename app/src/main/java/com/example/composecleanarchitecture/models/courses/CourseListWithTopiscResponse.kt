package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscResponse(
    @SerializedName("data")
    val `data`: CourseListWithTopiscData? = CourseListWithTopiscData()
)