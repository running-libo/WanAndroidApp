package com.example.flowmvihilt.qa

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.basemodule.base.BaseAdapter
import com.example.basemodule.base.BaseBindingFragment
import com.example.basemodule.base.LoadUiIntent
import com.example.basemodule.entity.DataBean
import com.example.flowmvihilt.databinding.FragmentQaBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class QaFragment: BaseBindingFragment<FragmentQaBinding>(
    { FragmentQaBinding.inflate(it)}
){
    @Inject
    lateinit var qaAdapter: QaAdapter
    private val QaVm by viewModels<QaViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        QaVm.sendUiIntent(QaIntent.getDatas(0))

        binding.rvQa.adapter = qaAdapter

        observe()

        qaAdapter.setOnClickListener(object: BaseAdapter.OnItemClickListener {
            override fun <E> onItemClick(data: E) {
                findNavController().navigate(com.example.resmodule.R.id.navigation_webview, Bundle().apply {
                    putString("url", (data as DataBean).link)
                })
            }
        })
    }

    private fun observe() {
        lifecycleScope.launch {
            QaVm.uiStateFlow.map { it.detailUiState }
                .collect { detailUiState ->
                    when(detailUiState) {
                        is DetailUiState.INIT -> {

                        }
                        is DetailUiState.SUCCESS -> {
                            binding.rvQa.visibility = View.VISIBLE
                            qaAdapter.setList(detailUiState.data)
                        }

                        else -> {}
                    }
                }
        }

        lifecycleScope.launch {
            QaVm.loadUiIntentFlow.collect { state ->
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