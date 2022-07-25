package com.example.movie.view.fragment.home.viewpager

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.view.fragment.home.HomeAdapter
import com.example.movie.view.fragment.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    var page = 1

//    private lateinit var nowplayingAdapter: HomeAdapter

    private val nowplayingAdapter = HomeAdapter()
    private val topratedAdapter = HomeAdapter()
    private val popularAdapter = HomeAdapter()

    override val bindingInflater: (LayoutInflater) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        setupView()
    }

    override fun observeViewModel() {
        viewModel.getNowPlaying(page)
        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        nowplayingAdapter.addData(data)
                    } else
                        Log.e("rvKosong", "getMovie: $data")
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        //Toast
                    }
                }
                is NetworkResult.Loading -> {
                    Log.e("shimmer", "getMovie: shimer")
                }
            }
        }

        viewModel.getTopRated(page)
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        topratedAdapter.addData(data)
                    }
                }
            }
        }

        viewModel.getPopular(page)
        viewModel.popular.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        popularAdapter.addData(data)
                    }
                }
            }
        }

    }

    override fun getDataFromIntent() {
//        TODO("Not yet implemented")
    }

    private fun setupView() {

//        popularAdapter.apply {
//
//        }

        binding?.layoutNowPlaying?.apply {
            txtCategory.text = "Now Playing"
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = nowplayingAdapter
            }
        }

        binding?.layoutTopRated?.apply {
            txtCategory.text = "Top Rated"
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = topratedAdapter
            }
        }

        binding?.layoutPopular?.apply {
            txtCategory.text = "Popular"
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = popularAdapter
            }
        }

    }


}