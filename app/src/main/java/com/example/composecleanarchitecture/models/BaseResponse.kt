package com.example.composecleanarchitecture.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
open class BaseResponse(
    @SerializedName("status_message") val status_message: String? = null,
    @SerializedName("code") val code: Int? = null,
    @SerializedName("messages") val messages: List<String>? = null,
    @SerializedName("message") val message: String? = null,
) : Parcelable