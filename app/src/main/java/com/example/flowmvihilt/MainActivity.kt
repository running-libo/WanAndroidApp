package com.example.flowmvihilt

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.flowmvihilt.base.BaseBindingActivity
import com.example.flowmvihilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        //将Navigation与BNV关联
//        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container_view) as NavHostFragment
//        val navController = navHostFragment.findNavController()
//        binding.navView.setupWithNavController(navController)

//        binding.navView.setOnItemSelectedListener {
//            when (it.itemId) {
//                R.id.navigation_home -> switchFragment(0)
//                R.id.navigation_qa -> switchFragment(1)
//                R.id.navigation_system -> switchFragment(2)
//                R.id.navigation_mine -> switchFragment(3)
//            }
//            true
//        }

        initViewPager()
    }

//    private fun switchFragment(position: Int) = binding.mainViewPager.setCurrentItem(position, true)

    private fun initViewPager() {
//        binding.mainViewPager.isUserInputEnabled = false
//        binding.mainViewPager.offscreenPageLimit = 2
//        binding.mainViewPager.adapter = object : FragmentStateAdapter(this) {
//            override fun getItemCount(): Int = 5
//
//            override fun createFragment(position: Int): Fragment = when (position) {
//                0 -> MainFragment()
//                1 -> MainFragment()
//                2 -> MainFragment()
//                3 -> MainFragment()
//                else -> MainFragment()
//            }
//
//        }
    }
}