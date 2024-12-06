package com.example.testdkatalis.base

import android.content.Context
import com.example.testdkatalis.MainActivity
import com.example.testdkatalis.app.App
import com.example.testdkatalis.network.ApiClient
import com.example.testdkatalis.network.ClientInterface


open class BasePresenter<T:BaseView> {
    var view: T? = null

    var retrofitInterface: ClientInterface = ApiClient.getRetrofit(App.instance).create(ClientInterface::class.java)

    val isViewNotNull: Boolean
        get() = view != null

    fun attachView(view: T) {
        this.view = view
    }

    fun dettachView() {
        this.view = null
    }
}

