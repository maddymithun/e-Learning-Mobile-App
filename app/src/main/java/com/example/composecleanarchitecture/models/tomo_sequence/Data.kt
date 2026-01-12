package com.example.composecleanarchitecture.models.tomo_sequence


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("previousYearQuestions")
    val previousYearQuestions: PreviousYearQuestions? = PreviousYearQuestions()
)