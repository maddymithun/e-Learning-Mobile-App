package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsDataX(
    @SerializedName("attributes")
    val attributes: PrevYerBCSQusQuizDetailsAttributes? = PrevYerBCSQusQuizDetailsAttributes(),
    @SerializedName("id")
    val id: String? = ""
)