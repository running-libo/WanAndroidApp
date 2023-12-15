package com.example.flowmvihilt

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.basemodule.util.ActivityManager.exitApp
import com.example.basemodule.util.ActivityManager.finishAll
import com.example.basemodule.util.GrayManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import java.lang.System.exit

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var navView: BottomNavigationView
    /** 连续按返回键退出时间  */
    private val EXIT_TIME = 2000
    /** 上次点击返回键时间  */
    private var lastTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        navView = findViewById(R.id.nav_view)
        initNavigationView()
        isGrayMode()
    }

    private fun isGrayMode() {
        var isGrayMode = getSharedPreferences("mode", MODE_PRIVATE).getBoolean("mode", false)
        if (isGrayMode) {
            //当前为
            GrayManager.setColorThemeMode(window.decorView)
        }
    }

    private fun initNavigationView() {
        //将Navigation与BNV关联
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navView.setupWithNavController(navHostFragment.findNavController())
    }

    /**
     * 双击返回退出App
     */
    override fun onBackPressed() {
        if (System.currentTimeMillis() - lastTime > EXIT_TIME) {
            Toast.makeText(applicationContext, R.string.exit_app, Toast.LENGTH_SHORT).show()
            lastTime = System.currentTimeMillis()
        } else {
            finishAll()
            exitApp()
            super.onBackPressed()
        }
    }

}