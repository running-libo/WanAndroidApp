package com.example.flowmvihilt.system

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.basemvi.BaseAdapter
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.basemvi.LoadUiIntent
import com.example.flowmvihilt.databinding.FragmentSystemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SystemFragment: BaseBindingFragment<FragmentSystemBinding>(
    { FragmentSystemBinding.inflate(it)}
){
    @Inject
    lateinit var sysAdapter: SystemAdapter
    private val sysVm by viewModels<SysViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sysVm.sendUiIntent(SysIntent.getDatas(0))

        binding.rvSystem.adapter = sysAdapter

        observe()

        sysAdapter.setOnClickListener(object: BaseAdapter.OnItemClickListener {
            override fun <E> onItemClick(data: E) {

            }
        })
    }

    private fun observe() {
        lifecycleScope.launch {
            sysVm.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.INIT -> {

                        }
                        is DetailUiState.SUCCESS -> {
                            binding.rvSystem.visibility = View.VISIBLE
                            sysAdapter.setList(detailUiState.data)
                        }

                        else -> {}
                    }
                }
        }

        lifecycleScope.launch {
            sysVm.loadUiIntentFlow.collect { state ->
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
}