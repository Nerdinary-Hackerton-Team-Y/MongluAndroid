package com.project.presentation

import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.project.presentation.databinding.ActivityMainBinding
import com.project.presentation.util.invisible
import com.project.presentation.util.navigateWithClear
import com.project.presentation.util.visible

class MainActivity : AppCompatActivity(), NavColorSet {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val navHostFragment by lazy {
        supportFragmentManager.findFragmentById(R.id.fragmentContainerView_main) as NavHostFragment
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        initView()
    }

    private fun initView() = with(binding) {
        fun initNavigation() {
            val navItems = listOf(
                clMainHome,
                clMainSnow,
                clMainUpload,
                clMainMypage
            )

            val navController = navHostFragment.navController
            val currentDestinationId = navController.currentDestination?.id

            navItems.forEach {
                it.setOnClickListener {
                    when (it.id) {
                        R.id.cl_main_home -> {
                            if (currentDestinationId != R.id.nav_home)
                                navController.navigateWithClear(R.id.nav_home)
                        }

                        R.id.cl_main_snow -> {
                            if (currentDestinationId != R.id.nav_feed)
                                navController.navigateWithClear(R.id.nav_feed)
                        }

                        R.id.cl_main_upload -> {
                            if (currentDestinationId != R.id.nav_postRegister)
                                navController.navigateWithClear(R.id.nav_postRegister)
                        }

                        R.id.cl_main_mypage -> {
                            if (currentDestinationId != R.id.nav_mypage)
                                navController.navigateWithClear(R.id.nav_mypage)
                        }
                    }
                }
            }

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.nav_home,
                    R.id.nav_feed,
                    R.id.nav_mypage -> this@MainActivity.visible()

                    else -> this@MainActivity.invisible()
                }
            }
        }

        setNavHome()
        initNavigation()
    }

    private fun updateNavIconTint(selected: ImageView) {
        listOf(
            binding.ivMainHome,
            binding.ivMainSnow,
            binding.ivMainUpload,
            binding.ivMainMypage
        ).forEach {
            it.setImageTintList(ColorStateList.valueOf(Color.GRAY))
        }
        selected.setImageTintList(ColorStateList.valueOf(Color.BLACK))
    }

    override fun setNavHome() {
        updateNavIconTint(binding.ivMainHome)
    }

    override fun setNavSnow() {
        updateNavIconTint(binding.ivMainSnow)
    }

    override fun setNavMypage() {
        updateNavIconTint(binding.ivMainMypage)
    }

    override fun setNavUpload() {
        updateNavIconTint(binding.ivMainUpload)
    }
}