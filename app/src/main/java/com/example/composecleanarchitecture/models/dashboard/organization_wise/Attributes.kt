package com.example.composecleanarchitecture.models.dashboard.organization_wise


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = ""
)