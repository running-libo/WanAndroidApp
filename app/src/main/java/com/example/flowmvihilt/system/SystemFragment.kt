package com.example.flowmvihilt.system

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.basemodule.base.BaseBindingFragment
import com.example.basemodule.base.LoadUiIntent
import com.example.flowmvihilt.databinding.FragmentSystemBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SystemFragment: BaseBindingFragment<FragmentSystemBinding>(
    { FragmentSystemBinding.inflate(it)}
){
    private val sysVm by viewModels<SysViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        sysVm.sendUiIntent(SysIntent.getDatas(0))

        observe()
    }

    private fun observe() {
        lifecycleScope.launch {
            sysVm.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.INIT -> {

                        }
                        is DetailUiState.SUCCESS -> {
                            Toast.makeText(context, "请求到了" + detailUiState.data.size + "条数据", Toast.LENGTH_SHORT).show()
//                            binding.recyclerView.visibility = View.VISIBLE
//                            articleAdapter.setList(detailUiState.data.datas)
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