package com.example.flowmvihilt.mine

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.flowmvihilt.R
import com.example.flowmvihilt.base.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentMeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MeFragment: BaseBindingFragment<FragmentMeBinding>(
    { FragmentMeBinding.inflate(it)}
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        findNavController().navigate(R.id.action_to_artical)
    }
}