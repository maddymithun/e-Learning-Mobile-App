package com.example.composecleanarchitecture.models.previous_year_question


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class PrevYerBCSQusQuizDetailsAttributes(
    @PrimaryKey(autoGenerate = true)
    val questionSlId: Int?=1,
    @SerializedName("designation")
    val designation: PrevYerBCSQusQuizDetailsDesignation? = PrevYerBCSQusQuizDetailsDesignation(),
    @SerializedName("grade")
    val grade: PrevYerBCSQusQuizDetailsGrade? = PrevYerBCSQusQuizDetailsGrade(),
    @SerializedName("organization")
    val organization: PrevYerBCSQusQuizDetailsOrganization? = PrevYerBCSQusQuizDetailsOrganization(),
    @SerializedName("question")
    val question: PrevYerBCSQusQuizDetailsQuestion? = PrevYerBCSQusQuizDetailsQuestion(),
    @SerializedName("sequence")
    val sequence: String? = "",
    @SerializedName("subject")
    val subject: PrevYerBCSQusQuizDetailsQuestionSubject? = PrevYerBCSQusQuizDetailsQuestionSubject(),
    @SerializedName("year")
    val year: Int? = 0
)