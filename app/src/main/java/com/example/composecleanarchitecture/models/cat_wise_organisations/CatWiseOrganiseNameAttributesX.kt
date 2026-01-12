package com.example.composecleanarchitecture.models.cat_wise_organisations


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CatWiseOrganiseNameAttributesX(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = ""
)

@Entity
data class PvsErIBAPscOrgName(
    @PrimaryKey(autoGenerate = true) val columnId: Int = 0,
    val code: String,
    val name: String,
    val queryCode: String
)

