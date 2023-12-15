package com.example.flowmvihilt.system

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowmvihilt.databinding.ItemSystemBinding
import com.example.flowmvihilt.databinding.ItemTagviewBinding
import com.example.basemodule.entity.SystemData
import javax.inject.Inject

class SystemAdapter @Inject constructor(): RecyclerView.Adapter<SystemAdapter.SysViewHolder>() {
    private var mList: List<SystemData> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SysViewHolder {
        return SysViewHolder(
            ItemSystemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SysViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnClickListener {

        }
    }

    fun setList(list: List<SystemData>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class SysViewHolder(val binding: ItemSystemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(bean: SystemData) {
            with(bean) {
                binding.tvTitle.text = name

                binding.flowLayoutView.removeAllViews()

                children.forEach {
                    val tagBinding = ItemTagviewBinding.inflate(LayoutInflater.from(binding.flowLayoutView.context))
                    tagBinding.tvContent.apply {
                        text = it.name
                        if (parent != null) {
                            (parent as ViewGroup).removeView(this)
                        }
                        binding.flowLayoutView.addView(this)
                    }
                }
            }
        }
    }
}
