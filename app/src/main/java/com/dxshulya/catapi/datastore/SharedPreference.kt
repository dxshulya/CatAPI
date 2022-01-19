package com.dxshulya.catapi.datastore

import android.content.Context

class SharedPreference(context: Context) {

    companion object {
        private const val EMAIL = "e-mail"
        private const val DESCRIPTION = "description"
        private const val APIKEY = "api-key"
    }

    private var sharedPreferences = context.getSharedPreferences("user-pref", Context.MODE_PRIVATE)
    private var sharedPreferencesEditor = sharedPreferences.edit()

    private fun setString(key: String, value: String) {
        sharedPreferencesEditor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    var description: String
        get() = getString(DESCRIPTION)
        set(value) {
            setString(DESCRIPTION, value)
        }
    var email: String
        get() = getString(EMAIL)
        set(value) {
            setString(EMAIL, value)
        }
    var apikey: String
        get() = getString(APIKEY)
        set(value) {
            setString(APIKEY, value)
        }
}