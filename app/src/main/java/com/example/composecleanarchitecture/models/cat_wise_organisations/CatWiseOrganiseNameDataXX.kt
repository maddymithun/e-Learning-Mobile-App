package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameDataXX(
    @SerializedName("attributes")
    val attributes: CatWiseOrganiseNameAttributesX? = CatWiseOrganiseNameAttributesX(),
    @SerializedName("id")
    val id: String? = ""
)