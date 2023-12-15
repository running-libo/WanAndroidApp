package com.example.flowmvihilt.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basemodule.basemvi.BaseAdapter
import com.example.flowmvihilt.databinding.ItemArticleBinding
import com.example.basemodule.entity.DataX
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class ArticleAdapter @Inject constructor(@ActivityContext val context: Context): BaseAdapter<ArticleAdapter.ArticleViewHolder, DataX>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnItemClick(position)
    }

    class ArticleViewHolder(val binding: ItemArticleBinding) : RecyclerView.ViewHolder(binding.root) {

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
