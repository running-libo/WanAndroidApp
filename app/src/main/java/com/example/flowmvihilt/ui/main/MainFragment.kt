package com.example.flowmvihilt.ui.main

import android.os.Bundle
import android.view.View
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import com.example.basemodule.base.BaseBindingFragment
import com.example.basemodule.base.LoadUiIntent
import com.example.basemodule.entity.BannerData
import com.example.flowmvihilt.databinding.FragmentMainBinding
import com.example.basemodule.util.dip2pxInt
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

        setBannerAdapter()
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
                    is LoadUiIntent.ShowPageView -> {

                    }
                }
            }
        }
    }

    private fun setBannerAdapter() {
        with(binding.viewpager) {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
//            setAutoTurningTime(3000)
//            startTurning()
//            marginLeft = context.dip2pxInt(20f)
//            marginRight = context.dip2pxInt(20f)
//            setPageMargin(context.dip2pxInt(20f), context.dip2pxInt(10f)) //设置左右页面露出来的宽度及item与item之间的宽度
//            addPageTransformer(ScaleInTransformer()) //内置ScaleInTransformer，设置切换缩放动画

            var datas = ArrayList<BannerData>()

            adapter = BannerAdapter(context, datas)
        }
    }
}