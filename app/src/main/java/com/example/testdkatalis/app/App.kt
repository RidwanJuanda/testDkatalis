package com.example.testdkatalis.app

import androidx.appcompat.app.AppCompatDelegate
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.example.testdkatalis.data.SharePref

class App : MultiDexApplication() {
    companion object {
        lateinit var instance: App

    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        MultiDex.install(this)
        SharePref.with(this)

    }
}