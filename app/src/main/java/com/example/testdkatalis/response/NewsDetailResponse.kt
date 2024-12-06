package com.example.testdkatalis.response

import com.example.testdkatalis.response.model.NewsData
import com.google.gson.annotations.SerializedName

data class NewsDetailResponse(
    @SerializedName("data")
    val data: NewsData? = null
): BaseResponses()