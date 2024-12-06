package com.example.testdkatalis.base


interface BaseView{
    fun onLoading(isShow: Boolean)
    fun onNoData(msg: String?)
    fun onBadRequest(msg: String?)
    fun onUnauthorized(msg: String?)
}