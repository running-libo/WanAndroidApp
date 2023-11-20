package com.example.flowmvihilt.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flowmvihilt.base.BaseBindingFragment
import com.example.flowmvihilt.base.DetailUiState
import com.example.flowmvihilt.base.LoadUiIntent
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

        binding.recyclerView.adapter = articleAdapter

        mainVm.sendUiIntent(MainIntent.getDetail(0))

        observe()
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
            mainVm.loadUiIntentFlow.collect { state ->
                when(state) {
                    is LoadUiIntent.Loading -> {

                    }
                    is LoadUiIntent.Error -> {

                    }
                    is LoadUiIntent.ShowMainView -> {

                    }
                }
            }
        }
    }
}