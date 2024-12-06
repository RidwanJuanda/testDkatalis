package com.example.testdkatalis.ui.detailnews

import com.example.testdkatalis.base.BasePresenter
import com.example.testdkatalis.response.NewsDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailNewsPresenter(view: DetailNewsView) : BasePresenter<DetailNewsView>() {
    private val TAG = "DetailNewsPresenter"

    init {
        super.attachView(view)
    }

    fun getNewsDetail(slug: String){
        view?.onLoading(true)
        val request = retrofitInterface.getNewsDetail(slug)
        request.enqueue(object : Callback<NewsDetailResponse> {
            override fun onResponse(call: Call<NewsDetailResponse>, response: Response<NewsDetailResponse>) {
                view?.onLoading(false)
                if (response.isSuccessful){
                    view?.onGetNewsDetail(response.body()?.data)
                }
            }

            override fun onFailure(call: Call<NewsDetailResponse>, t: Throwable) {
                view?.onLoading(false)
            }

        })
    }
}