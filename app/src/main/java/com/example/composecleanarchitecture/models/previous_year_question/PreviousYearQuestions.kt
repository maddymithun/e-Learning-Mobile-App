package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsPreviousYearQuestions(
    @SerializedName("data")
    val `data`: List<PrevYerBCSQusQuizDetailsDataX>? = listOf(),
    @SerializedName("meta")
    val meta: PrevYerBCSQusQuizDetailsMeta? = PrevYerBCSQusQuizDetailsMeta()
)