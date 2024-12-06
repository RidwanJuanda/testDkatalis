package com.example.testdkatalis.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.testdkatalis.base.BaseRecyclerViewAdapter
import com.example.testdkatalis.base.BaseViewHolder
import com.example.testdkatalis.databinding.ItemNewsOtherBinding
import com.example.testdkatalis.response.model.NewsData

class NewsOtherAdapter(val callback: (String) -> Unit): BaseRecyclerViewAdapter<NewsData>(){

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<NewsData> {
        return PopularNewsViewHolder(
            ItemNewsOtherBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class PopularNewsViewHolder(val view:ItemNewsOtherBinding):BaseViewHolder<NewsData>(view.root){
        @SuppressLint("SetTextI18n")
        override fun bindData(data: NewsData) {
            view.apply {
                val imageData = data.images?.shuffled()?.find { true }
                tvTitleNews.text = data.title
                tvDate.text = data.startDateEvent.toString()
                if (!data.author?.name.isNullOrEmpty())
                    tvWriter.text = "Penulis : ${data.author?.name}"
                else if (data.persons.isNotEmpty())
                    tvWriter.text = "Penulis : ${data.persons}"
                else
                    tvWriter.text = "Penulis : - "
                Glide.with(view.root).load(imageData).into(ivNews)
                view.root.setOnClickListener {
                    data.slugUrl?.let { it1 -> callback(it1) }
                }
            }
        }
    }
}