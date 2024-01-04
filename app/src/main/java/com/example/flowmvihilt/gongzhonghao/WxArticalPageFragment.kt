package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class WxArticalPageFragment: Fragment() {

    private val viewModel by viewModels<WxArticalViewModel>()

    companion object {
        val recyclerViewPool = RecyclerView.RecycledViewPool()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        init()
        observe()
    }

    fun init() {
        var articalId = arguments?.getInt("id", 0)
        viewModel.sendUiIntent(WxArticalIntent.getDataList(articalId!!, 0))
    }

    private fun observe() {
        lifecycleScope.launch {
            viewModel.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.LIST_SUCCESS -> {
                            
                        }

                        else -> {

                        }
                    }
                }
        }
    }
}