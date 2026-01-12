package com.example.composecleanarchitecture.utils

import com.google.gson.GsonBuilder

fun <T> T.makeJson(isPretty: Boolean = false): String {
    return if (isPretty)
        GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(this)
    else
        GsonBuilder().serializeNulls().create().toJson(this)
}

inline fun <reified T : Any> String.makeObject(): T {
    return GsonBuilder().serializeNulls().create().fromJson(
        this,
        T::class.java
    )
}
