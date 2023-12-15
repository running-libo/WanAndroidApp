package com.example.flowmvihilt.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.basemodule.base.BaseBindingFragment
import com.example.basemodule.base.LoadUiIntent
import com.example.basemodule.entity.BannerData
import com.example.flowmvihilt.R
import com.example.flowmvihilt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment: BaseBindingFragment<FragmentMainBinding>(
    {FragmentMainBinding.inflate(it)}
) {

    @Inject
    lateinit var articleAdapter: ArticleAdapter

    private val mainVm by viewModels<MainVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        mainVm.sendUiIntent(MainIntent.getDetail(0))
        mainVm.sendUiIntent(MainIntent.getBanner)

        observe()

        initAdapter()
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = articleAdapter
        articleAdapter.setOnClickListener(object: ArticleAdapter.OnItemClickListener {
            override fun onItemClick(url: String) {
                findNavController().navigate(com.example.resmodule.R.id.navigation_webview, Bundle().apply {

                })
            }
        })
    }

    private fun observe() {
        lifecycleScope.launch {
            mainVm.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.INIT -> {

                        }
                        is DetailUiState.SUCCESS -> {
                            binding.recyclerView.visibility = View.VISIBLE
                            articleAdapter.setList(detailUiState.data.datas)
                        }
                    }
                }
        }

        lifecycleScope.launch {
            mainVm.uiStateFlow.map { it.bannerUiState }
                .collect { bannerUiState ->
                    when(bannerUiState) {
                        is BannerUiState.INIT -> {

                        }
                        is BannerUiState.SUCCESS -> {
                            setBannerAdapter(bannerUiState.data)
                        }
                    }
                }
        }

        lifecycleScope.launch {
            mainVm.loadUiIntentFlow.collect { state ->
                when(state) {
                    is LoadUiIntent.Loading -> {

                    }
                    is LoadUiIntent.Error -> {

                    }
                    is LoadUiIntent.ShowPageView -> {

                    }
                }
            }
        }
    }

    private fun setBannerAdapter(datas: ArrayList<BannerData>) {
        with(binding.viewpager) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            offscreenPageLimit = 1
            var recyclerView = getChildAt(0) as RecyclerView
            var padding = 80
            recyclerView.setPadding(padding, 0, padding, 0)  //设置左右页面露出来的宽度及item与item之间的宽度
            recyclerView.clipToPadding = false
            adapter = BannerAdapter(context, datas)
        }
    }
}