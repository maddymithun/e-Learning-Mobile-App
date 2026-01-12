package com.example.composecleanarchitecture.db.shared_preference

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class SharedPreferencesHelper (private val context: Context) {
    private val preferencesHelper: SharedPreferences
    private val localPref = "localPref"


    init {
        preferencesHelper = context.getSharedPreferences(localPref, Context.MODE_PRIVATE)
    }

    fun putString(key: String, value: String?) {
        preferencesHelper.edit().putString(key, value).apply()
    }

    fun putInt(key: String, value: Int) {
        preferencesHelper.edit().putInt(key, value).apply()
    }

    fun putLong(key: String, value: Long) {
        preferencesHelper.edit().putLong(key, value).apply()
    }


    fun putBool(key: String, value: Boolean) {
        preferencesHelper.edit().putBoolean(key, value).apply()
    }

    operator fun get(key: String, defaultValue: String): String {
        return preferencesHelper.getString(key, defaultValue) ?: defaultValue
    }

    operator fun get(key: String, defaultValue: Int): Int {
        return preferencesHelper.getInt(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Float): Float {
        return preferencesHelper.getFloat(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Boolean): Boolean {
        return preferencesHelper.getBoolean(key, defaultValue)
    }

    operator fun get(key: String, defaultValue: Long): Long {
        return preferencesHelper.getLong(key, defaultValue)
    }

    fun <T> getResponse(key: String, clazz: Class<T>): T? {
        return try {
            val response = preferencesHelper.getString(key, "") ?: ""
            Gson().fromJson(response, clazz)
        } catch (e: Exception) {
            null
        }
    }

    fun deleteData(key: String) {
        preferencesHelper.edit().remove(key).apply()
    }

    fun clearAll(){
        preferencesHelper.edit().clear().apply()
    }

}