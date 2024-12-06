package com.example.testdkatalis.response

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class BannersResponses(
    @SerializedName("data")
    val data: List<BannersData>? = null
) : BaseResponses()

@Parcelize
data class BannersData(
    @SerializedName("id")
    val id: Int = 0,

    @SerializedName("title")
    val title: String? = null,

    @SerializedName("sub_title")
    val subTitle: String? = null,

    @SerializedName("category")
    val category: String? = null,

    @SerializedName("link")
    val link: String? = null,

    @SerializedName("images")
    val image: String? = null,

) : Parcelable