package com.example.composecleanarchitecture.models.designations


import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class OrgWiseDesignationListResponseAttributesX(
    @SerializedName("code")
    val code: String? = "",
    @SerializedName("name")
    val name: String? = "",
)

@Entity
data class DesignationDataModel(
    @PrimaryKey(autoGenerate = true)
    val columnId: Int = 0,
    val categoryCode: String,
    val code: String,
    val name: String
)