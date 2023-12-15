package com.example.basemodule.basemvi

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter <VH : RecyclerView.ViewHolder?, T> : RecyclerView.Adapter<VH>() {
    protected var mList: ArrayList<T> = ArrayList()
    protected var itemClickListener: OnItemClickListener?= null

    fun setOnClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    fun setList(list: List<T>) {
        mList.clear()
        mList.addAll(list)
        notifyDataSetChanged()
    }

    fun appendList(list: List<T>) {
        mList.addAll(list)
        notifyDataSetChanged()
    }

    override fun getItemCount() = mList.size

    interface OnItemClickListener {
        fun <E> onItemClick(e: E)
    }
}