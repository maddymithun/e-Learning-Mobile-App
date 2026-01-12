package com.example.composecleanarchitecture.models.tomo_sequence


import com.google.gson.annotations.SerializedName

data class PreviousYearQuestions(
    @SerializedName("data")
    val `data`: List<DataX>? = listOf(),
    @SerializedName("meta")
    val meta: Meta? = Meta()
)