package com.example.composecleanarchitecture.models.dashboard.previous_year


import com.google.gson.annotations.SerializedName

data class DashBoardPrevErQuest(
    @SerializedName("data")
    val `data`: Data? = Data()
)