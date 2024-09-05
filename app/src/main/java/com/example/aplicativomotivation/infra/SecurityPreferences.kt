package com.example.aplicativomotivation.infra

import android.content.Context
import android.content.SharedPreferences

class SecurityPreferences(context: Context) {

    private val security: SharedPreferences =
        context.getSharedPreferences("Motivation", Context.MODE_PRIVATE)


    fun storeString(key: String, str: String) {
        security.edit().putString(key, str).apply()
    }

    fun getStoredString(key: String): String {
        return this.security.getString(key, "Insira seu nome.") ?: ""
    }

}