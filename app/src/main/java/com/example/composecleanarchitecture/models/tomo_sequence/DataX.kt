package com.example.composecleanarchitecture.models.tomo_sequence


import com.google.gson.annotations.SerializedName

data class DataX(
    @SerializedName("attributes")
    val attributes: Attributes? = Attributes(),
    @SerializedName("id")
    val id: String? = ""
)