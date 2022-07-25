package com.example.movie.view.activity.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.movie.R
import com.example.movie.base.BaseActivity
import com.example.movie.databinding.ActivityMainBinding
import com.example.movie.view.fragment.home.HomeFragment
import com.example.movie.view.fragment.search.SearchFragment
import com.example.movie.view.fragment.upcoming.UpComingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val homeFragment: Fragment = HomeFragment()
    private val searchFragment: Fragment = SearchFragment()
    private val upcomingFragment: Fragment = UpComingFragment()

    private val fm: FragmentManager = supportFragmentManager

    var defaultFragment: Fragment = homeFragment
    lateinit var menu: Menu
    lateinit var menuItem: MenuItem

    override val setLayout: (LayoutInflater) -> ActivityMainBinding
        get() = ActivityMainBinding::inflate


    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        setupBotNav()

    }

    private fun setupBotNav() {
        fm.beginTransaction().add(R.id.flContainer, homeFragment).show(homeFragment).commit()
        fm.beginTransaction().add(R.id.flContainer, searchFragment).hide(searchFragment).commit()
        fm.beginTransaction().add(R.id.flContainer, upcomingFragment).hide(upcomingFragment)
            .commit()

        menu = binding.botNav.menu
        menuItem = menu.getItem(0)
        menuItem.isChecked = true

        binding.botNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menuHome -> {
                    callFragment(0, homeFragment)
                }
                R.id.menuSearch -> {
                    callFragment(1, searchFragment)
                }
                R.id.menuUpcoming -> {
                    callFragment(2, upcomingFragment)
                }
            }
            false
        }

    }

    private fun callFragment(position: Int, fragment: Fragment) {
        menuItem = menu.getItem(position)
        menuItem.isChecked = true
        fm.beginTransaction().hide(defaultFragment).show(fragment).commit()
        defaultFragment = fragment
    }


}

