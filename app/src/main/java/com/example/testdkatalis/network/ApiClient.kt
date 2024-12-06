package com.example.testdkatalis.network

import android.content.Context
import android.util.Log
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.example.testdkatalis.BuildConfig
import com.example.testdkatalis.data.Const
import com.example.testdkatalis.data.SharePref
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ApiClient {

    companion object {
        private const val TIMEOUT: Long = 30
        private var singleton: Retrofit? = null
        private var gson: Gson = GsonBuilder().setLenient().create()

        fun getRetrofit(context: Context): Retrofit {
            if (singleton == null) {
                synchronized(this) {
                    singleton = Retrofit.Builder()
                        .baseUrl(URLConfig.HTTP + URLConfig.SERVICES_URL)
                        .addConverterFactory(GsonConverterFactory.create(gson))
                        .client(getClient(context))
                        .build()
                }
            }
            return singleton!!
        }

        private fun getClient(context:Context): OkHttpClient {
            val logging = HttpLoggingInterceptor()
            logging.level = if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor.Level.BODY
            } else {
                HttpLoggingInterceptor.Level.NONE
            }
            return OkHttpClient().newBuilder()
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(logging)
                .addInterceptor(ChuckerInterceptor(context))
                .addInterceptor { chain ->
                    val token = SharePref.getString(Const.TOKEN)
                    Log.d("TOKEN", "TOKEN: $token ")
                    val original = chain.request()
                    val request = original.newBuilder()
                        .addHeader("Authorization", "Bearer $token")
                        .method(original.method, original.body)
                        .build()
                    chain.proceed(request)
                }
                .build()
        }
    }
}