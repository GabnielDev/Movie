package com.example.movie.view.fragment.home

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import com.example.movie.base.BaseFragment
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.view.fragment.home.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        setupViewPager()

    }

//    private fun setupSlider() {
//        val sliderImage = SliderImage(view!!.context)
//
//        val images = listOf(
//            "https://media.suara.com/pictures/653x366/2022/06/02/81185-one-piece-screenshotone-piece-official-youtube-channel.webp",
//            "https://selular.id/wp-content/uploads/2022/03/ILUSTRASI-manga-One-Piece-chapter-1044.jpg"
//        )
//
//        binding?.slider?.setItems(listOf(
//            "https://media.suara.com/pictures/653x366/2022/06/02/81185-one-piece-screenshotone-piece-official-youtube-channel.webp",
//            "https://selular.id/wp-content/uploads/2022/03/ILUSTRASI-manga-One-Piece-chapter-1044.jpg"
//        ))
//
//    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        with(binding) {
            this?.viewPager?.adapter = viewPagerAdapter
            this?.viewPager?.isUserInputEnabled = false

            this?.tabLayout?.let {
                it.setSelectedTabIndicatorColor(R.color.transparent)
                TabLayoutMediator(it, viewPager) { tab, position ->
                    when (position) {
                        0 -> tab.text = "Movie"
                        1 -> tab.text = "Tv"
                    }
                }.attach()
            }

        }
    }

    override fun observeViewModel() {
        //        TODO("Not yet implemented")
    }

    override fun getDataFromIntent() {
//        TODO("Not yet implemented")
    }


}