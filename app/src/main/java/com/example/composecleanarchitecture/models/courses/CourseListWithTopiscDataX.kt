package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscDataX(
    @SerializedName("attributes")
    val attributes: CourseListWithTopiscAttributes? = CourseListWithTopiscAttributes(),
    @SerializedName("id")
    val id: String? = ""
)