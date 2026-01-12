package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsData(
    @SerializedName("previousYearQuestions")
    val previousYearQuestions: PrevYerBCSQusQuizDetailsPreviousYearQuestions? = PrevYerBCSQusQuizDetailsPreviousYearQuestions()
)