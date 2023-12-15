package com.example.basemodule.base

import androidx.recyclerview.widget.RecyclerView

abstract class BaseAdapter <VH : RecyclerView.ViewHolder?> : RecyclerView.Adapter<VH>() {
    protected var itemClickListener: OnItemClickListener?= null

    fun setOnClickListener(itemClickListener: OnItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    interface OnItemClickListener {
        fun <E> onItemClick(e: E)
    }
}