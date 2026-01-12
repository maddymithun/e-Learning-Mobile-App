package com.example.composecleanarchitecture.models.previous_year_question

import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsQuestionSubject(
    @SerializedName("data")
    val data: PrevYerBCSQusQuizDetailsQuestionSubjectData? = PrevYerBCSQusQuizDetailsQuestionSubjectData()
)
