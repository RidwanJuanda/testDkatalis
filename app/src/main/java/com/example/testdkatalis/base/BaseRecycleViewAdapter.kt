package com.example.testdkatalis.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseRecyclerViewAdapter<T> : RecyclerView.Adapter<BaseViewHolder<T>>() {

    private var data: MutableList<T> = mutableListOf()
    private var lastPosition = 0


    open fun setData(data: List<T>) {
        this.data = data as MutableList<T>
        notifyDataSetChanged()
    }

    fun removeAllData() {
        this.data.clear()
        notifyItemRangeRemoved(0, data.size)
    }

    fun getData(): MutableList<T> {
        return this.data
    }

    fun addData(data: List<T>) {
        lastPosition = this.data.lastIndex
        this.data.addAll(data)
        notifyItemRangeInserted(lastPosition + 1, data.size)
    }

    fun addDataList(data: List<T>){
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T {
        return data[position]
    }


    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: BaseViewHolder<T>, position: Int) {
        holder.bindData(getItem(position))
    }
}