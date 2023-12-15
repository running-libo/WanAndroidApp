package com.example.flowmvihilt.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.basemodule.entity.BannerData
import com.example.flowmvihilt.databinding.ItemBannerBinding
import javax.inject.Inject

/**
 * create by apple
 * create on 2021/7/2
 * description
 */
class BannerAdapter @Inject constructor(val context: Context, val datas: ArrayList<BannerData>): RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        var view = layoutInflater!!.inflate(R.layout.item_banner, parent, false)
        return ViewHolder(ItemBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindData()

    }

    override fun getItemCount() = datas.size

    fun addDatas(datas: List<BannerData>) {
        this.datas.addAll(datas)
        notifyDataSetChanged()
    }

    class ViewHolder(val binding: ItemBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindData() {
//            Glide.with(context).load(datas[position].imagePath).into(binding.ivCover)

            binding.ivCover.setOnClickListener {
//            ARouter.getInstance().build("/article/webpage")
//                .withString("url", datas[position].url)
//                .navigation()
            }
        }
    }

}