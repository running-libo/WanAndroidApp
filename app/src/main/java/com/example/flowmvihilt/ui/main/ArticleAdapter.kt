package com.example.flowmvihilt.ui.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowmvihilt.databinding.ItemArticleBinding
import com.example.flowmvihilt.domain.entity.DataX
import javax.inject.Inject

class ArticleAdapter @Inject constructor(): RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {
    private var mList: List<DataX> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        return ArticleViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnClickListener {

        }
    }

    fun setList(list: List<DataX>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
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
