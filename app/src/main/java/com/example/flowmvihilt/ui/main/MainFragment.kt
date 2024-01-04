package com.example.flowmvihilt.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.basemvi.LoadUiIntent
import com.example.basemodule.entity.BannerData
import com.example.basemodule.entity.DataX
import com.example.flowmvihilt.databinding.FragmentMainBinding
import com.example.flowmvihilt.searchengine.SearchActivity
import com.example.flowmvihilt.webview.WebViewActivity.Companion.gotoWebView
import com.scwang.smart.refresh.layout.api.RefreshLayout
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener
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
    @Inject
    lateinit var bannerAdapter: BannerAdapter

    private val mainVm by viewModels<MainVM>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainVm.sendUiIntent(MainIntent.getDetail(0))
        mainVm.sendUiIntent(MainIntent.getBanner)

        observe()

        initAdapter()
        setRefreshLoadMore()

        binding.ivGotoSearch.setOnClickListener {
            startActivity(Intent(activity, SearchActivity::class.java))
        }
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = articleAdapter
        articleAdapter.setOnClickListener(object: BaseAdapter.OnItemClickListener {

            override fun <E> onItemClick(data: E) {
                activity?.gotoWebView((data as DataX).link)
            }
        })
        bannerAdapter.setOnClickListener(object: BaseAdapter.OnItemClickListener {
            override fun <E> onItemClick(data: E) {
                activity?.gotoWebView((data as BannerData).url)
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
                            if (detailUiState.page == 0) {
                                articleAdapter.setList(detailUiState.data.datas)
                                binding.smartRefreshLayout.finishRefresh()
                            } else {
                                articleAdapter.appendList(detailUiState.data.datas)
                                binding.smartRefreshLayout.finishLoadMore()
                            }
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
                        if (!state.isShow) {
                            binding.smartRefreshLayout.finishRefresh()
                            binding.smartRefreshLayout.finishLoadMore()
                        }
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
            bannerAdapter.setList(datas)
            adapter = bannerAdapter
        }
    }

    private fun setRefreshLoadMore() {
        binding.smartRefreshLayout.setOnRefreshLoadMoreListener(object: OnRefreshLoadMoreListener {
            override fun onRefresh(refreshLayout: RefreshLayout) {
                mainVm.refresh { page ->
                    mainVm.sendUiIntent(MainIntent.getDetail(page))
                }
            }

            override fun onLoadMore(refreshLayout: RefreshLayout) {
                mainVm.loadMore { page ->
                    mainVm.sendUiIntent(MainIntent.getDetail(page))
                }
            }
        })
    }
}