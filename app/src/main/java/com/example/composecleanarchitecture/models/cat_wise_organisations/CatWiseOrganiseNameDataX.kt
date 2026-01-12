package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameDataX(
    @SerializedName("attributes")
    val attributes: CatWiseOrganiseNameAttributes? = CatWiseOrganiseNameAttributes(),
    @SerializedName("id")
    val id: String? = ""
)