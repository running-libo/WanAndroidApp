package com.example.flowmvihilt.ui.main

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.flowmvihilt.base.BaseBindingFragment
import com.example.flowmvihilt.base.DetailUiState
import com.example.flowmvihilt.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map

@AndroidEntryPoint
class MainFragment: BaseBindingFragment<FragmentMainBinding>(
    {FragmentMainBinding.inflate(it)}
) {

    private val mainVm by viewModels<MainVM>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mainVm.sendUiIntent(MainIntent.getDetail(0))

        lifecycleScope.launchWhenStarted {
            mainVm.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.INIT -> {

                        }
                        is DetailUiState.SUCCESS -> {
                            binding.recyclerView.visibility = View.VISIBLE
                        }
                    }
                }
        }
    }
}