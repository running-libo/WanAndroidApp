package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentGongzhongBinding
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class GongzhongFragment : BaseBindingFragment<FragmentGongzhongBinding>({
    FragmentGongzhongBinding.inflate(it)
}) {

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private val titles = arrayOf("关注 128", "粉丝 128", "推荐关注","关注 128", "粉丝 128", "推荐关注","关注 128", "粉丝 128", "推荐关注")

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
    }

    fun init() {
        for (i in titles.indices) {
            fragments.add(GongzhonghaoPageFragment())
            binding.tablayout.addTab(binding.tablayout.newTab().setText(titles[i]))
        }
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, titles)
        binding.viewpager.adapter = pagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)
    }
}