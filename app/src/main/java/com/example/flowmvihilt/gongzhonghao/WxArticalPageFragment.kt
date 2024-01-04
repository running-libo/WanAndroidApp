package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView

class WxArticalPageFragment: Fragment() {

    companion object {
        val recyclerViewPool = RecyclerView.RecycledViewPool()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }
    fun init() {
    }
}