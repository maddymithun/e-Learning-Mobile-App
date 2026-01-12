package com.example.composecleanarchitecture.models.institute_qus_year


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


data class CategoryWiseExmYearListAttributesX(
    @SerializedName("year")
    val year: Int? = 0,
)

@Entity
data class YearsOfBankInsEduForPvsErQues(
    @PrimaryKey(autoGenerate = true)
    val columnId: Int = 0,
    val year: Int,
    val queryCode: String
)