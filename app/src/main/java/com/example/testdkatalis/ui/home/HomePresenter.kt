package com.example.testdkatalis.ui.home

import android.util.Log
import com.example.testdkatalis.base.BasePresenter
import com.example.testdkatalis.data.Const
import com.example.testdkatalis.response.BannersResponses
import com.example.testdkatalis.response.NewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomePresenter(view: HomeView) : BasePresenter<HomeView>() {
    private val TAG = "HomePresenter"

    init {
        super.attachView(view)
    }

    fun getBanner() {
        view?.onLoading(true)
        val request = retrofitInterface.getBanner()
        request.enqueue(object : Callback<BannersResponses> {
            override fun onResponse(
                call: Call<BannersResponses>,
                response: Response<BannersResponses>
            ) {
                view?.onLoading(false)
                when (response.code()) {
                    Const.OK -> view?.onGetBanners(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<BannersResponses>, t: Throwable) {
                view?.onLoading(false)
                Log.e(TAG, "getBanner: ${t.cause}",t )
            }

        })
    }

    fun getNewsTop() {
        val request = retrofitInterface.getNewsTop(1, 20, "MONTH", "image")
        request.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    view?.onGetTopNews(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "getNews: ${t.cause}", t)
            }

        })
    }

    fun getNewsOther() {
        val request = retrofitInterface.getNewsOther(1, 20)
        request.enqueue(object : Callback<NewsResponse> {
            override fun onResponse(
                call: Call<NewsResponse>,
                response: Response<NewsResponse>
            ) {
                if (response.isSuccessful) {
                    view?.onGetOtherNews(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<NewsResponse>, t: Throwable) {
                Log.e(TAG, "getNews: ${t.cause}", t)
            }

        })
    }
}