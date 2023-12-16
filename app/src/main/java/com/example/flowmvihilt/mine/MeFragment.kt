package com.example.flowmvihilt.mine

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.util.LanguageHelper
import com.example.flowmvihilt.databinding.FragmentMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeFragment: BaseBindingFragment<FragmentMeBinding>(
    { FragmentMeBinding.inflate(it)}
) {

    private val meVm by viewModels<MeViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isGrayMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("mode", false)
        binding.viewSwitchGray.isChecked = isGrayMode!!

        var isLoadPicMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("loadPic", true)
        binding.viewSwitchPic.isChecked = isLoadPicMode!!

        //灰色模式切换
        binding.viewSwitchGray.setOnCheckedChangeListener { _, isChecked ->
            context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.edit()?.putBoolean("mode", isChecked)?.commit()
        }

        //智能无图模式切换
        binding.viewSwitchPic.setOnCheckedChangeListener { _, isChecked ->
            context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.edit()?.putBoolean("loadPic", isChecked)?.commit()
        }

        binding.viewSwitchNight.setOnCheckedChangeListener { _, isChecked ->
            //自 AppCompat v1.1.0 起，setDefaultNightMode() 会自动重新建立任何启动的活动
//            meVm.switchNightMode(isChecked)
        }

        binding.tvCurLanguage.text = LanguageHelper.getSystemLanguage()
    }
}