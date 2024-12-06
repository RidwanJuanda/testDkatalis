package com.example.testdkatalis.ui.home

import com.example.testdkatalis.base.BaseView
import com.example.testdkatalis.response.BannersData
import com.example.testdkatalis.response.model.MagazineCategory
import com.example.testdkatalis.response.model.NewsData

interface HomeView: BaseView {
    fun onGetBanners(data:List<BannersData>?)
    fun onGetTopNews(data:List<NewsData>?)
    fun onGetOtherNews(data:List<NewsData>?)
}