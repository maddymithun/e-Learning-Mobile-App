package com.example.composecleanarchitecture.models.institute_qus_year


import com.google.gson.annotations.SerializedName

data class CategoryWiseExmYearListPreviousYearQuestions(
    @SerializedName("data")
    val `data`: List<CategoryWiseExmYearListDataXX>? = listOf()
)