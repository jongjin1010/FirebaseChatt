package com.jjoh.firebasechatt.view.chat

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoh.firebasechatt.R
import com.jjoh.firebasechatt.base.BaseActivity
import com.jjoh.firebasechatt.databinding.ActivityMainBinding


class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    private lateinit var homeFragment: HomeFragment
    private lateinit var chatFragment: ChatFragment
    private lateinit var profileFragment: ProfileFragment

    override fun init() {
        binding.activity = this
        binding.bottomNav.setOnNavigationItemSelectedListener(bottomNavItemSelectedListener)
        homeFragment = HomeFragment.newInstance()
        supportFragmentManager.beginTransaction().add(R.id.fragments_frame, homeFragment).commit()
    }

    private val bottomNavItemSelectedListener =
        BottomNavigationView.OnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.menu_home -> {
                    homeFragment = HomeFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, homeFragment).commit()
                }
                R.id.menu_chat -> {
                    chatFragment = ChatFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, chatFragment).commit()
                }
                R.id.menu_profile -> {
                    profileFragment = ProfileFragment.newInstance()
                    supportFragmentManager.beginTransaction().replace(R.id.fragments_frame, profileFragment).commit()
                }
            }
            val b = true
            b
        }

}