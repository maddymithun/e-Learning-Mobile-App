package com.example.composecleanarchitecture.models.tomo_sequence


import com.google.gson.annotations.SerializedName

data class TomoBcsSequenceResponse(
    @SerializedName("data")
    val `data`: Data? = Data()
)