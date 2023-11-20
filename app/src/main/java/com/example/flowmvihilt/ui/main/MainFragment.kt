package com.example.flowmvihilt.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flowmvihilt.base.BaseBindingFragment
import com.example.flowmvihilt.base.DetailUiState
import com.example.flowmvihilt.databinding.FragmentMainBinding
import com.example.flowmvihilt.main.ArticleAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment: BaseBindingFragment<FragmentMainBinding>(
    {FragmentMainBinding.inflate(it)}
) {

    private val mainVm by viewModels<MainVM>()
    private lateinit var articleAdapter: ArticleAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        articleAdapter = ArticleAdapter()
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
    }
}