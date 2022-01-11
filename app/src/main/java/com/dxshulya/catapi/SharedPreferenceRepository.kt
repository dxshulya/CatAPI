package com.dxshulya.catapi

import android.content.Context

class SharedPreferenceRepository (context: Context) : SharedPreferenceInterface {

    companion object {
        private const val EMAIL = "e-mail"
        private const val DESCRIPTION = "description"
        private const val APIKEY = "api-key"
    }

    private var sharedPreferences = context.getSharedPreferences("sp_dagger", Context.MODE_PRIVATE)
    private var sharedPreferencesEditor = sharedPreferences.edit()

    private fun setString(key: String, value: String) {
        sharedPreferencesEditor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    override var description: String
        get() = getString(DESCRIPTION)
        set(value) {
            setString(DESCRIPTION, value)
        }
    override var email: String
        get() = getString(EMAIL)
        set(value) {
            setString(EMAIL, value)
        }
    override var apikey: String
        get() = getString(APIKEY)
        set(value) {
            setString(APIKEY, value)
        }
}