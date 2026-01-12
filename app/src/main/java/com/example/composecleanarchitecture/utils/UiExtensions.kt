package com.example.composecleanarchitecture.utils

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.net.ConnectivityManager
import android.widget.DatePicker
import android.widget.Toast
import com.example.composecleanarchitecture.base.BaseActivity
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun Context.showToast(text: String, time: Int = Toast.LENGTH_SHORT) =
    Toast.makeText(this, text, time).show()

@SuppressLint("SimpleDateFormat")
fun Context.datePicker(onDatePick: (code: String) -> Unit): DatePickerDialog {
    val mYear: Int
    val mMonth: Int
    val mDay: Int
    val now = Calendar.getInstance()
    mYear = now.get(Calendar.YEAR)
    mMonth = now.get(Calendar.MONTH)
    mDay = now.get(Calendar.DAY_OF_MONTH)
    now.time = Date()
    return DatePickerDialog(
        this,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val cal = Calendar.getInstance()
            cal.set(year, month, dayOfMonth)
            onDatePick(SimpleDateFormat("dd-MM-yyyy").format(cal.time))
        }, mYear, mMonth, mDay
    )
}


fun Context.changeLanguage(locale: Locale, isActivityRestart: Boolean = true) = run {
    try {
        (this as BaseActivity).updateLocale(locale, isActivityRestart)
    } catch (e: Exception) {
        this.showToast(e.message.toString())
    }
}

fun String.convertEngToBanDigit(): String {
    return this.replace("0".toRegex(), "০")
        .replace("1".toRegex(), "১")
        .replace("2".toRegex(), "২")
        .replace("3".toRegex(), "৩")
        .replace("4".toRegex(), "৪")
        .replace("5".toRegex(), "৫")
        .replace("6".toRegex(), "৬")
        .replace("7".toRegex(), "৭")
        .replace("8".toRegex(), "৮")
        .replace("9".toRegex(), "৯")

}

fun Context.isInternetAvailable(): Boolean {
    val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetworkInfo = connectivityManager.activeNetworkInfo
    return activeNetworkInfo != null && activeNetworkInfo.isConnected
}

fun String.convertAsExpectedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.US)
    val outputFormat = SimpleDateFormat("dd MMM, yyyy", Locale.US)
    return try {
        val date = inputFormat.parse(this)
        outputFormat.format(date!!)
    } catch (e: Exception) {
        this
    }
}

fun getYearsArray(): Array<String> {
    val currentYear = Calendar.getInstance().get(Calendar.YEAR)
    val endYear = 1990
    val yearCount = currentYear - endYear + 1
    return Array(yearCount) { (currentYear - it).toString() }
}