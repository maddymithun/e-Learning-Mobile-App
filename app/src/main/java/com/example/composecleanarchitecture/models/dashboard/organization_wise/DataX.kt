package com.example.composecleanarchitecture.models.dashboard.organization_wise


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("attributes")
    val attributes: Attributes? = Attributes(),
    @SerializedName("id")
    val id: String? = ""
)