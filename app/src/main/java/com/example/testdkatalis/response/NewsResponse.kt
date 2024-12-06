package com.example.testdkatalis.response

import android.os.Parcelable
import com.example.testdkatalis.response.model.Meta
import com.example.testdkatalis.response.model.NewsData
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class NewsResponse(
    @SerializedName("data")
    val data: List<NewsData>? = null,

    @SerializedName("meta")
    val meta: Meta? = null
): BaseResponses(), Parcelable