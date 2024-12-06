package com.example.testdkatalis.response

import com.google.gson.annotations.SerializedName


open class BaseResponses{
    @SerializedName("status")
    val status: Int = 200
    @SerializedName("message")
    val message: String? = null
}