package com.example.composecleanarchitecture.models.dashboard.previous_year


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("attributes")
    val attributes: Attributes? = Attributes(),
    @SerializedName("id")
    val id: String? = ""
)