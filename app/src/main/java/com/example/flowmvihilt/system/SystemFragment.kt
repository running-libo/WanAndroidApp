package com.example.flowmvihilt.system

import android.os.Bundle
import android.view.View
import com.example.flowmvihilt.base.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentSystemBinding

class SystemFragment: BaseBindingFragment<FragmentSystemBinding>(
    { FragmentSystemBinding.inflate(it)}
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}