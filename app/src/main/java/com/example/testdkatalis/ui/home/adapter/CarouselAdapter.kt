package com.example.testdkatalis.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testdkatalis.base.BaseRecyclerViewAdapter
import com.example.testdkatalis.base.BaseViewHolder
import com.example.testdkatalis.databinding.ItemCarouselHomeBinding
import com.example.testdkatalis.response.BannersData

class CarouselAdapter(val callback: (BannersData) -> Unit): BaseRecyclerViewAdapter<BannersData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<BannersData> {
        return CarouselViewHolder(
            ItemCarouselHomeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    inner class CarouselViewHolder(val view: ItemCarouselHomeBinding):BaseViewHolder<BannersData>(view.root){
        override fun bindData(data: BannersData) {
            view.apply {
                Glide.with(view.root)
                    .load(data.image)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(image)
                view.root.setOnClickListener {
                    callback(data)
                }
            }
        }
    }
}