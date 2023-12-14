package com.example.flowmvihilt.qa

import android.os.Bundle
import android.view.View
import com.example.basemodule.base.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentQaBinding

class QaFragment: BaseBindingFragment<FragmentQaBinding>(
    { FragmentQaBinding.inflate(it)}
){

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }
}