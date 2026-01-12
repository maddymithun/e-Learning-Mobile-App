package com.example.composecleanarchitecture.models.cat_wise_organisations


import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameAttributes(
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("organizations")
    val catWiseOrganiseNameOrganizations: CatWiseOrganiseNameOrganizations? = CatWiseOrganiseNameOrganizations()
)