package com.example.flowmvihilt.gongzhonghao

import android.os.Bundle
import android.view.View
import com.example.basemodule.basemvi.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentGongzhongBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GongzhongFragment : BaseBindingFragment<FragmentGongzhongBinding>({
    FragmentGongzhongBinding.inflate(it)
}) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }
}