package com.example.composecleanarchitecture.models.institute_qus_year


import com.google.gson.annotations.SerializedName

data class CategoryWiseExmYearListAttributes(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("previous_year_questions")
    val previousYearQuestions: CategoryWiseExmYearListPreviousYearQuestions? = CategoryWiseExmYearListPreviousYearQuestions()
)