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

        var isGrayMode = context?.getSharedPreferences("mode", Context.MODE_PRIVATE)?.getBoolean("mode", false)
        binding.viewSwitch.isChecked = isGrayMode!!

        binding.viewSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            context?.getSharedPreferences("mode", Context.MODE_PRIVATE)?.edit()?.putBoolean("mode", isChecked)?.commit()
        }

        binding.tvCurLanguage.text = LanguageHelper.getSystemLanguage()
//        findNavController().navigate(R.id.action_to_artical)
    }
}