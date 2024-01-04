package com.example.flowmvihilt

import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.basemodule.util.ActivityManager.exitApp
import com.example.basemodule.util.ActivityManager.finishAll
import com.example.basemodule.util.GrayManager
import com.example.flowmvihilt.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    /** 连续按返回键退出时间  */
    private val EXIT_TIME = 2000
    /** 上次点击返回键时间  */
    private var lastTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setStatusBarColor(resources.getColor(com.example.resmodule.R.color.theme_color));
        initNavigationView()
        isGrayMode()
    }

    private fun isGrayMode() {
        var isGrayMode = getSharedPreferences("wan_sp", MODE_PRIVATE).getBoolean("mode", false)
        if (isGrayMode) {
            //当前为
            GrayManager.setColorThemeMode(window.decorView)
        }
    }

    private fun initNavigationView() {
        //将Navigation与BNV关联
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        binding.navView.setupWithNavController(navHostFragment.findNavController())
    }

    /**
     * 双击返回退出App
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            Toast.makeText(applicationContext, com.example.resmodule.R.string.exit_app, Toast.LENGTH_SHORT).show()
            lastTime = System.currentTimeMillis()
        } else {
            finishAll()
            exitApp()
            super.onBackPressed()
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private fun setStatusBarColor(color: Int) {
        val window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = color
    }

}