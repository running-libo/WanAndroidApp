package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.entity.WxArticalResponse
import com.example.flowmvihilt.databinding.FragmentGongzhongBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WxArticalFragment : BaseBindingFragment<FragmentGongzhongBinding>({
    FragmentGongzhongBinding.inflate(it)
}) {

    private val fragments = ArrayList<Fragment>()
    private var pagerAdapter: CommPagerAdapter? = null
    private val viewModel by viewModels<WxArticalViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.sendUiIntent(WxArticalIntent.getDatas(0))
        observe()

    }

    fun init(datas: List<WxArticalResponse>) {
        var strArray = ArrayList<String>()
        datas.forEach {
            fragments.add(WxArticalPageFragment())
            binding.tablayout.addTab(binding.tablayout.newTab().setText(it.name))
            strArray.add(it.name)
        }
        pagerAdapter = CommPagerAdapter(childFragmentManager, fragments, strArray)
        binding.viewpager.adapter = pagerAdapter
        binding.tablayout.setupWithViewPager(binding.viewpager)
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.SUCCESS -> {
                            init(detailUiState.data)
                        }

                        else -> {

                        }
                    }
                }
        }
    }
}