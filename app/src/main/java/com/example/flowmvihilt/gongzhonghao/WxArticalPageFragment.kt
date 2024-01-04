package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.entity.DataX
import com.example.flowmvihilt.databinding.FragmentGongzhongPageBinding
import com.example.flowmvihilt.webview.WebViewActivity.Companion.gotoWebView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class WxArticalPageFragment: BaseBindingFragment<FragmentGongzhongPageBinding>({
    FragmentGongzhongPageBinding.inflate(it)
}) {

    private val viewModel by viewModels<WxArticalViewModel>()
    @Inject
    lateinit var adapter:WxArticleAdapter

    companion object {
        val recyclerViewPool = RecyclerView.RecycledViewPool()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        initAdapter()
        observe()
    }

    fun init() {
        var articalId = arguments?.getInt("id", 0)
        viewModel.sendUiIntent(WxArticalIntent.getDataList(articalId!!, 0))
    }

    private fun initAdapter() {
        binding.recyclerView.adapter = adapter
        binding.recyclerView.setRecycledViewPool(recyclerViewPool)
        adapter.setOnClickListener(object: BaseAdapter.OnItemClickListener {
            override fun <E> onItemClick(data: E) {
                activity?.gotoWebView((data as DataX).link)
            }
        })
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.LIST_SUCCESS -> {
                            adapter.setList(detailUiState.data)
                        }

                        else -> {

                        }
                    }
                }
        }
    }
}