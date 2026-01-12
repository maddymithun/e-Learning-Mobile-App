package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameCategories(
    @SerializedName("data")
    val `data`: List<CatWiseOrganiseNameDataX>? = listOf()
)