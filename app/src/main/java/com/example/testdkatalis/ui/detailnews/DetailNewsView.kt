package com.example.testdkatalis.ui.detailnews

import com.example.testdkatalis.base.BaseView
import com.example.testdkatalis.response.BannersData
import com.example.testdkatalis.response.model.MagazineCategory
import com.example.testdkatalis.response.model.NewsData

interface DetailNewsView: BaseView {
    fun onGetNewsDetail(data: NewsData?)
}