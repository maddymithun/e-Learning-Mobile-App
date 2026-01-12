package com.example.composecleanarchitecture.models.previous_year_question


import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsMeta(
    @SerializedName("pagination")
    val pagination: PrevYerBCSQusQuizDetailsPagination? = PrevYerBCSQusQuizDetailsPagination()
)