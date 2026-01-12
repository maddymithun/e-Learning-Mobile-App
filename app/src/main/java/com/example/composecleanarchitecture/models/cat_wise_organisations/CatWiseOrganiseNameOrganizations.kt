package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameOrganizations(
    @SerializedName("data")
    val `data`: List<CatWiseOrganiseNameDataXX>? = listOf()
)