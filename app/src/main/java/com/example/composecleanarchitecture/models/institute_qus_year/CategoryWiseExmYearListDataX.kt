package com.example.composecleanarchitecture.models.institute_qus_year


import com.google.gson.annotations.SerializedName

data class CategoryWiseExmYearListDataX(
    @SerializedName("attributes")
    val attributes: CategoryWiseExmYearListAttributes? = CategoryWiseExmYearListAttributes(),
    @SerializedName("id")
    val id: String? = ""
)