package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameData(
    @SerializedName("categories")
    val categories: CatWiseOrganiseNameCategories? = CatWiseOrganiseNameCategories()
)