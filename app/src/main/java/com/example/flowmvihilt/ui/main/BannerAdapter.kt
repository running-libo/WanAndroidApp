package com.example.flowmvihilt.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.entity.BannerData
import com.example.flowmvihilt.databinding.ItemBannerBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

/**
 * create by apple
 * create on 2021/7/2
 * description
 */
class BannerAdapter @Inject constructor(@ActivityContext val context: Context): BaseAdapter<BannerAdapter.ViewHolder, BannerData>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData(mList[position])
    }

    class ViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData(data: BannerData) {
            binding.ivCover.load(data.imagePath) {
                crossfade(true)
                .crossfade(200)  //淡入淡出时间
                transformations(RoundedCornersTransformation(10f))
            }

            binding.ivCover.setOnClickListener {

            }
        }
    }

}