package com.example.movie.presentation.home.viewpager

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.presentation.core.BaseFragment
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.databinding.FragmentTvBinding
import com.example.movie.helper.showToast
import com.example.movie.presentation.home.HomeViewModel
import com.example.movie.presentation.home.TvAdapter
import dagger.hilt.android.AndroidEntryPoint
import com.example.movie.R.string
import com.example.movie.data.remote.response.ResultTv

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    var page = 1

    override fun setLayout(inflater: LayoutInflater): FragmentTvBinding {
        return FragmentTvBinding.inflate(inflater)
    }

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {
        viewModel.getAiringtoday(page)
        viewModel.airingtoday.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupNowAiring(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutAiring.shimmerPoster.root.startShimmer()
                }
            }
        }

        viewModel.getTopRatedTv(page)
        viewModel.topratedTv.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupTopRated(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutTopRated.shimmerPoster.root.startShimmer()
                }
            }
        }

        viewModel.getOnTheAirTv(page)
        viewModel.ontheairTv.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupOnTheAir(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutOnTheAir.shimmerPoster.root.startShimmer()
                }
            }
        }

        viewModel.getPopularTv(page)
        viewModel.popularTv.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupPopular(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(context, it)
                    }
                }
                is NetworkResult.Loading -> {
                }
            }
        }

    }

    private fun setupView() {
        binding.layoutOnTheAir.txtCategory.text = getString(string.tv_category_ontheair)
        binding.layoutAiring.txtCategory.text = getString(string.tv_category_airingtoday)
        binding.layoutTopRated.txtCategory.text = getString(string.tv_category_toprated)
        binding.layoutPopular.txtCategory.text = getString(string.tv_category_popular)
    }

    private fun setupNowAiring(list: ArrayList<ResultTv>?) {
        val nowairingAdapter = TvAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.layoutAiring.apply {
            shimmerPoster.root.visibility = GONE
            rvPoster.apply {
                visibility = VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = nowairingAdapter
            }
        }
    }

    private fun setupTopRated(list: ArrayList<ResultTv>?) {
        val topratedAdapter = TvAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.layoutTopRated.apply {
            shimmerPoster.root.visibility = GONE
            rvPoster.apply {
                visibility = VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = topratedAdapter
            }
        }
    }

    private fun setupOnTheAir(list: ArrayList<ResultTv>?) {
        val ontheairAdapter = TvAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.layoutOnTheAir.apply {
            shimmerPoster.root.visibility = GONE
            rvPoster.apply {
                visibility = VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = ontheairAdapter
            }
        }
    }

    private fun setupPopular(list: ArrayList<ResultTv>?) {
        val popularAdapter = TvAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.layoutPopular.apply {
            shimmerPoster.root.visibility = GONE
            rvPoster.apply {
                visibility = VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
            }
        }

    }


}