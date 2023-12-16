package com.example.flowmvihilt.mine

import android.content.Context
import android.os.Bundle
import android.view.View
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.basemodule.util.LanguageHelper
import com.example.flowmvihilt.databinding.FragmentMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeFragment: BaseBindingFragment<FragmentMeBinding>(
    { FragmentMeBinding.inflate(it)}
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var isGrayMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("mode", false)
        binding.viewSwitchGray.isChecked = isGrayMode!!

        var isLoadPicMode = context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.getBoolean("loadPic", true)
        binding.viewSwitchPic.isChecked = isLoadPicMode!!

        binding.viewSwitchGray.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.edit()?.putBoolean("mode", isChecked)?.commit()
        }

        binding.viewSwitchPic.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.getSharedPreferences("wan_sp", Context.MODE_PRIVATE)?.edit()?.putBoolean("loadPic", isChecked)?.commit()
        }

        binding.tvCurLanguage.text = LanguageHelper.getSystemLanguage()
    }
}