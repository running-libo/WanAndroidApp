package com.example.flowmvihilt.qa

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.entity.DataBean
import com.example.flowmvihilt.databinding.ItemQuestionBinding
import javax.inject.Inject

class QaAdapter @Inject constructor(): BaseAdapter<QaAdapter.QaViewHolder>() {
    private var mList: List<DataBean> = ArrayList()

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

    fun setList(list: List<DataBean>) {
        mList = list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class QaViewHolder(val binding: ItemQuestionBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bindData(bean: DataBean) {
            with(bean) {
                binding.tvAuth.text = author
                binding.tvTitle.text = title
                binding.tvDate.text = niceDate
                binding.tvContent.text = desc
                binding.tvTag.text = chapterName
            }
        }
    }
}
