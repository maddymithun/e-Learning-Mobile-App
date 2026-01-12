package com.example.composecleanarchitecture.models.tomo_sequence


import com.google.gson.annotations.SerializedName

data class Attributes(
    @SerializedName("sequence")
    val sequence: String? = "",
    @SerializedName("year")
    val year: Int? = 0
)