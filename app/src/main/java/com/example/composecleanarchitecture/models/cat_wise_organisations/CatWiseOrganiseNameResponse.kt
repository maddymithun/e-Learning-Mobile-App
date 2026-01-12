package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameResponse(
    @SerializedName("data")
    val `data`: CatWiseOrganiseNameData? = CatWiseOrganiseNameData()
)