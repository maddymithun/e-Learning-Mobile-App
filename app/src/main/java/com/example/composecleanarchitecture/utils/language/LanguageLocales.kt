package com.sslwireless.androidarch.ui.util.language

import java.util.*

object LanguageLocales {
    val english: Locale by lazy { Locale("en", "US") }
    val bengali: Locale by lazy { Locale("bn", "BD") }

    val RTL: Set<String> by lazy {
        hashSetOf(
            "ar",
            "ku",
            "dv",
            "fa",
            "ha",
            "he",
            "iw",
            "ji",
            "ps",
            "sd",
            "ug",
            "ur",
            "yi"
        )
    }
}