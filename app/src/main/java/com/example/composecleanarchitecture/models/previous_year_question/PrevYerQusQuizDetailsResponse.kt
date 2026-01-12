package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerQusQuizDetailsResponse(
    @SerializedName("data")
    val `data`: PrevYerBCSQusQuizDetailsData? = PrevYerBCSQusQuizDetailsData()
)
