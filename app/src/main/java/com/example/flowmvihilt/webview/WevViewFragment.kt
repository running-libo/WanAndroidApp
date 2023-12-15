package com.example.flowmvihilt.webview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.basemodule.base.BaseBindingFragment
import com.example.flowmvihilt.databinding.FragmentWebviewBinding

class WevViewFragment : BaseBindingFragment<FragmentWebviewBinding>({
    FragmentWebviewBinding.inflate(it)
}) {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return super.onCreateView(inflater, container, savedInstanceState)
    }

}