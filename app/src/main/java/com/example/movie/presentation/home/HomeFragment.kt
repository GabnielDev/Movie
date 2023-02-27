package com.example.movie.presentation.home

import android.R
import com.example.movie.R.string
import android.view.LayoutInflater
import com.example.movie.presentation.core.BaseFragment
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.presentation.home.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun setLayout(inflater: LayoutInflater): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater)
    }

    override fun initialization() {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(parentFragmentManager, lifecycle)
        binding.run {
            this.viewPager.adapter = viewPagerAdapter
            this.viewPager.isUserInputEnabled = false

            this.tabLayout.let {
                it.setSelectedTabIndicatorColor(R.color.transparent)
                TabLayoutMediator(it, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = getString(string.category_movie)
                        1 -> tab.text = getString(string.category_tv)
                    }
                }.attach()
            }
        }
    }

    override fun observeViewModel() {

    }

}