package com.example.composecleanarchitecture.models.previous_year_question


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class PrevYerBCSQusQuizDetailsAttributesXXXX(
    @SerializedName("answer")
    val answer: String? = "",
    @SerializedName("option_a")
    val optionA: String? = "",
    @SerializedName("option_b")
    val optionB: String? = "",
    @SerializedName("option_c")
    val optionC: String? = "",
    @SerializedName("option_d")
    val optionD: String? = "",
    @SerializedName("option_e")
    val optionE: String? = "",
    @SerializedName("question")
    val question: String? = "",
    @SerializedName("details")
    val details: String? = ""
)

@Entity
data class PreviousYearQuestionDetailsFormat(
    @PrimaryKey(autoGenerate = true)
    val columnId: Int = 0,
    val lazyId: Int = 0,
    @SerializedName("answer")
    val answer: String,
    @SerializedName("option_a")
    val optionA: String,
    @SerializedName("option_b")
    val optionB: String,
    @SerializedName("option_c")
    val optionC: String,
    @SerializedName("option_d")
    val optionD: String,
    @SerializedName("option_e")
    val optionE: String? = "",
    @SerializedName("question")
    val question: String,
    @SerializedName("details")
    val details: String,
    val gradeName: String,
    val subjectName: String,
    val designationName: String,
    val organizationName: String,
    val year: String,
    val queryCode: String
)