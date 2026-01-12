package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsOrganization(
    @SerializedName("data")
    val `data`: PrevYerBCSQusQuizDetailsDataXX? = PrevYerBCSQusQuizDetailsDataXX()
)