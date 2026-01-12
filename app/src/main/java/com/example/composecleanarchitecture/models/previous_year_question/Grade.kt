package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsGrade(
    @SerializedName("data")
    val `data`: PrevYerBCSQusQuizDetailsDataXX? = PrevYerBCSQusQuizDetailsDataXX()
)