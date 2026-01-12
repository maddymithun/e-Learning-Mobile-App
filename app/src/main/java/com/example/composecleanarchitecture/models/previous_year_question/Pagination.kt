package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsPagination(
    @SerializedName("page")
    val page: Int? = 0,
    @SerializedName("pageCount")
    val pageCount: Int? = 0,
    @SerializedName("pageSize")
    val pageSize: Int? = 0,
    @SerializedName("total")
    val total: Int? = 0
)