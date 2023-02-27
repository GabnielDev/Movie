package com.example.movie.presentation.home.viewpager

import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.presentation.core.BaseFragment
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.helper.showToast
import com.example.movie.data.remote.response.ResultsItem
import com.example.movie.presentation.detailmovie.DetailMovieActivity
import com.example.movie.presentation.home.HomeAdapter
import com.example.movie.presentation.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    var page = 1

    private var testAdapter = HomeAdapter()

    override fun setLayout(inflater: LayoutInflater): FragmentMovieBinding {
        return FragmentMovieBinding.inflate(inflater)
    }

    override fun initialization() {
        setupView()

    }

    override fun observeViewModel() {
        viewModel.getNowPlaying(page)
        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupNowPlaying(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutNowPlaying.shimmerPoster.root.startShimmer()
                }
            }
        }

        viewModel.getTopRated(page)
        viewModel.topRated.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupTopRated(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutTopRated.shimmerPoster.root.startShimmer()
                }
            }
        }

        viewModel.getPopular(page)
        viewModel.popular.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupPopular(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.layoutPopular.shimmerPoster.root.startShimmer()
                }
            }
        }

    }

    private fun setupView() {
        binding.layoutPopular.txtCategory.text = getString(R.string.movie_category_popular)
        binding.layoutTopRated.txtCategory.text = getString(R.string.movie_category_toprated)
        binding.layoutNowPlaying.txtCategory.text = getString(R.string.movie_category_nowplaying)
    }

    private fun setupNowPlaying(list: ArrayList<ResultsItem>?) {
        val nowplayingAdapter = HomeAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = data.let {
                    DetailMovieActivity.newIntent(context, item?.id)
                }
                startActivity(intent)
            }
        }

        binding.layoutNowPlaying.apply {
            shimmerPoster.root.visibility = GONE
            rvPoster.apply {
                visibility = VISIBLE
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = nowplayingAdapter
            }
        }
    }

    private fun setupTopRated(list: ArrayList<ResultsItem>?) {
        val topratedAdapter = HomeAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = data.let {
                    DetailMovieActivity.newIntent(context, item?.id)
                }
                startActivity(intent)
            }
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

    private fun setupPopular(list: ArrayList<ResultsItem>?) {
        val popularAdapter = HomeAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = data.let {
                    DetailMovieActivity.newIntent(context, item?.id)
                }
                startActivity(intent)
            }
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