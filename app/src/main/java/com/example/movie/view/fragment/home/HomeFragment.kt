package com.example.movie.view.fragment.home

import android.R
import com.example.movie.R.string
import android.view.LayoutInflater
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import com.denzcoskun.imageslider.models.SlideModel
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentHomeBinding
import com.example.movie.helper.showToast
import com.example.movie.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.fragment.home.viewpager.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    companion object {
        private const val ARG_POSITION = "ARG_POSITION"

        fun getInstance(position: Int) = HomeFragment().apply {
            arguments = bundleOf(ARG_POSITION to position)
        }
    }

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    private val viewModel: HomeViewModel by viewModels()

    override val bindingInflater: (LayoutInflater) -> FragmentHomeBinding
        get() = FragmentHomeBinding::inflate

    override fun initialization() {
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPagerAdapter = ViewPagerAdapter(parentFragmentManager, lifecycle)

        with(binding) {
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
        viewModel.getTopRated(1)
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {

//                        setupTopRated(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
//                    binding.layoutTopRated.shimmerPoster.root.startShimmer()
                }
            }
        }
    }

}