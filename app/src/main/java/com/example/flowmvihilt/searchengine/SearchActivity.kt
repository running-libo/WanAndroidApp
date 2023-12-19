package com.example.flowmvihilt.searchengine

import android.os.Bundle
import com.example.basemodule.basemvi.BaseBindingActivity
import com.example.flowmvihilt.databinding.ActivitySearchBinding

class SearchActivity : BaseBindingActivity<ActivitySearchBinding>({
    ActivitySearchBinding.inflate(it)
}) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

}