package com.example.testdkatalis.data

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.GsonBuilder


object SharePref {
    lateinit var preferences: SharedPreferences

    fun with(application: Application) {
        preferences =
            application.getSharedPreferences("com.example.testdkatalis.data", Context.MODE_PRIVATE)
    }

    fun saveString(key: String, value: String?) {
        preferences.edit {
            putString(key, value).commit()
        }
    }

    fun saveInt(key: String, value: Int?) {
        preferences.edit {
            putInt(key, value ?: 0).commit()
        }
    }

    fun saveLong(key: String, value: Long?) {
        preferences.edit {
            putLong(key, value ?: 0).commit()
        }
    }

    fun saveBoolean(key: String, value: Boolean) {
        preferences.edit {
            putBoolean(key, value).commit()
        }
    }

    fun getString(key: String): String? {
        return preferences.getString(key, "")
    }

    fun getBoolean(key: String): Boolean {
        return preferences.getBoolean(key, false)
    }

    fun getInt(key: String): Int {
        return preferences.getInt(key, 0)
    }

    fun getLong(key: String): Long {
        return preferences.getLong(key, 0)
    }

    fun clear() {
        preferences.edit {
            clear().apply()
        }
    }

    fun remove(key: String) {
        preferences.edit {
            remove(key)
        }
    }

    fun <T> put(`object`: T, key: String) {
        val jsonString = GsonBuilder().create().toJson(`object`)
        preferences.edit {
            putString(key, jsonString).commit()
        }
    }

    inline fun <reified T> get(key: String): T? {
        val value = preferences.getString(key, null)
        return GsonBuilder().create().fromJson(value, T::class.java)
    }
}