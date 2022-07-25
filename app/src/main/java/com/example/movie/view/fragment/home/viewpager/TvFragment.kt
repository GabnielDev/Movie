package com.example.movie.view.fragment.home.viewpager

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentTvBinding
import com.example.movie.helper.showToast
import com.example.movie.view.fragment.home.HomeViewModel
import com.example.movie.view.fragment.home.TvAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.example.movie.R.string

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val airingtodayAdapter = TvAdapter()

    override val bindingInflater: (LayoutInflater) -> FragmentTvBinding
        get() = FragmentTvBinding::inflate

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {
        viewModel.getAiringtoday(page = 1)
        viewModel.airingtoday.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        airingtodayAdapter.addData(data)
                        binding.layoutAiring.apply {
                            shimmerPoster.root.visibility = GONE
                            rvPoster.visibility = VISIBLE
                        }
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutAiring.shimmerPoster.root.startShimmerAnimation()
                }
            }
        }
    }


    private fun setupView() {
        binding.layoutAiring.apply {
            txtCategory.text = getString(string.tv_category_airingtoday)
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = airingtodayAdapter
            }
        }
    }


}