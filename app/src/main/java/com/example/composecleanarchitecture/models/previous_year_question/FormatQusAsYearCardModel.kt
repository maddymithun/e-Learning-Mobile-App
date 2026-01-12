package com.example.composecleanarchitecture.models.previous_year_question

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class FormatQusAsYearCardModel(
    @SerializedName("data")
    val `data`: List<PrevYerBCSQusQuizDetailsDataX>? = listOf(),
    @SerializedName("year")
    var year: Int = 0,
    @SerializedName("organization")
    var organization: String = "0",
    @SerializedName("splitFrom")
    val splitFrom: Int,
    @SerializedName("splitTo")
    val splitTo: Int
)

@Entity
data class FormatQuestionsAsExpected(
    @PrimaryKey(autoGenerate = true) val id: Int=0,
    @SerializedName("queryCode")
    val queryCode: String,
    @SerializedName("data")
    val `data`: String,
    @SerializedName("year")
    var year: Int = 0,
    @SerializedName("organization")
    var organization: String = "0",
    @SerializedName("splitFrom")
    val splitFrom: Int,
    @SerializedName("splitTo")
    val splitTo: Int
)