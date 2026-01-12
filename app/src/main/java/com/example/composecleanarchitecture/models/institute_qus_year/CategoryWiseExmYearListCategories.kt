package com.example.composecleanarchitecture.models.institute_qus_year


import com.google.gson.annotations.SerializedName

data class CategoryWiseExmYearListCategories(
    @SerializedName("data")
    val `data`: List<CategoryWiseExmYearListDataX>? = listOf()
)