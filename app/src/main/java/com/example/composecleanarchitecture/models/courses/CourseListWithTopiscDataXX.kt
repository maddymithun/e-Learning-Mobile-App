package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscDataXX(
    @SerializedName("attributes")
    val attributes: CourseListWithTopiscAttributesX? = CourseListWithTopiscAttributesX(),
    @SerializedName("id")
    val id: String? = ""
)