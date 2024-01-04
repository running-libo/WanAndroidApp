package com.example.flowmvihilt.qa

import android.os.Build
import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.entity.DataBean
import com.example.flowmvihilt.databinding.ItemQuestionBinding
import javax.inject.Inject


class QaAdapter @Inject constructor(): BaseAdapter<QaAdapter.QaViewHolder, DataBean>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QaViewHolder {
        return QaViewHolder(
            ItemQuestionBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: QaViewHolder, position: Int) {
        holder.bindData(mList[position])
        holder.binding.root.setOnClickListener {
            itemClickListener?.onItemClick(mList[position])
        }
    }

    class QaViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(bean: DataBean) {
            with(bean) {
                binding.tvAuth.text = author
                binding.tvTitle.text = title
                binding.tvDate.text = niceDate
                binding.tvTag.text = chapterName

                // 设置 HTML 富文本内容到 TextView
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    binding.tvContent.text = Html.fromHtml(desc, Html.FROM_HTML_MODE_LEGACY)
                } else {
                    @Suppress("deprecation") val spanned = Html.fromHtml(desc)
                    binding.tvContent.text = spanned
                }
            }
        }
    }
}
