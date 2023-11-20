package com.example.flowmvihilt.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flowmvihilt.databinding.ItemArticleBinding
import com.example.flowmvihilt.domain.entity.DataX

class ArticleAdapter : RecyclerView.Adapter<ArticleAdapter.BannerViewHolder>() {
    private var mList: List<DataX> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BannerViewHolder {
        return BannerViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: BannerViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnClickListener {
//            it.context.startActivity(Intent(it.context, ArticleActivity::class.java))
        }
    }

    fun setList(list: List<DataX>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class BannerViewHolder(val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindData(article: DataX) {
            binding.title.text = article.title
        }
    }
}
