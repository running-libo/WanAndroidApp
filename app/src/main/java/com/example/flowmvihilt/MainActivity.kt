package com.example.flowmvihilt

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.flowmvihilt.base.BaseBindingActivity
import com.example.flowmvihilt.databinding.ActivityMainBinding
import com.example.flowmvihilt.ui.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseBindingActivity<ActivityMainBinding>(
    {ActivityMainBinding.inflate(it)}
) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.navView.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> switchFragment(0)
                R.id.navigation_blog -> switchFragment(1)
                R.id.navigation_search -> switchFragment(2)
                R.id.navigation_project_type -> switchFragment(3)
                R.id.navigation_me -> switchFragment(4)
            }
            true
        }

        initViewPager()
    }

    private fun switchFragment(position: Int) = binding.mainViewPager.setCurrentItem(position, true)

    private fun initViewPager() {
        binding.mainViewPager.isUserInputEnabled = false
        binding.mainViewPager.offscreenPageLimit = 2
        binding.mainViewPager.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 5

            override fun createFragment(position: Int): Fragment = when (position) {
                0 -> MainFragment()
                1 -> MainFragment()
                2 -> MainFragment()
                3 -> MainFragment()
                4 -> MainFragment()
                else -> MainFragment()
            }

        }
    }
}