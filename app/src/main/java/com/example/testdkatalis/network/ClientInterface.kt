package com.example.testdkatalis.network

import com.example.testdkatalis.response.BannersResponses
import com.example.testdkatalis.response.NewsDetailResponse
import com.example.testdkatalis.response.NewsResponse
import retrofit2.Call
import retrofit2.http.*

interface ClientInterface {
    @GET("v1/banners")
    fun getBanner(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 10
    ): Call<BannersResponses>

    @GET("v1/magazine/top")
    fun getNewsTop(
        @Query("page") page: Int? = 1,
        @Query("pageSize") pageSize: Int? = 10,
        @Query("dateType") dateType: String? = null,
        @Query("typeThumbnail") typeThumbnail: String? = null,
    ): Call<NewsResponse>

    @GET("v1/magazine")
    fun getNewsOther(
        @Query("page") page: Int? = 2,
        @Query("pageSize") pageSize: Int? = 10,
        @Query("category_id") id: Int? = null,
        @Query("title") title: String? = null,
        @Query("typeThumbnail") typeThumbnail: String? = null,
    ): Call<NewsResponse>

    @GET("v1/magazine/get")
    fun getNewsDetail(
        @Query("slug_url") slugUrl: String
    ): Call<NewsDetailResponse>
}