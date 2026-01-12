package com.example.composecleanarchitecture.models.courses


import com.google.gson.annotations.SerializedName

data class CourseListWithTopiscMeta(
    @SerializedName("pagination")
    val pagination: CourseListWithTopiscPagination? = CourseListWithTopiscPagination()
)