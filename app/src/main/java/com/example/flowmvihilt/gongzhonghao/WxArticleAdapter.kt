package com.example.flowmvihilt.gongzhonghao

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.entity.DataX
import com.example.flowmvihilt.databinding.ItemWxarticleBinding
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class WxArticleAdapter @Inject constructor(@ActivityContext val context: Context): BaseAdapter<WxArticleAdapter.ArticleViewHolder, DataX>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemWxarticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnItemClick(position)
    }

    class ArticleViewHolder(val binding: ItemWxarticleBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(article: DataX) {
            with(article) {
                binding.tvTitle.text = title
                binding.tvAuth.text = author
                binding.tvContent.text = desc
                binding.tvDate.text = niceDate
                binding.tvTag.text = chapterName
            }
        }
    }

}
