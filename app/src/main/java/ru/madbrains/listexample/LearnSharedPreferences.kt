@file:Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")

package ru.madbrains.listexample

import android.content.Context
import android.content.SharedPreferences


fun getSharedPreference(context: Context): SharedPreferences {
    return context.getSharedPreferences("Pref", Context.MODE_PRIVATE)
}

fun savePreference(context: Context, key: String, text: String) {
    val preferenceInstance = getSharedPreference(context)
    val editor = preferenceInstance.edit()
    editor.putString(key, text)
    editor.apply()
}

fun loadPreference(context: Context, key: String): String {
    val preferenceInstance = getSharedPreference(context)
    return preferenceInstance.getString(key, "defaultValue")
}

