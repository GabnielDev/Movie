package com.example.movie.view.fragment.home.viewpager

import android.util.Log
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentMovieBinding
import com.example.movie.helper.showToast
import com.example.movie.remote.response.ResultsItem
import com.example.movie.view.activity.detail.DetailMovieActivity
import com.example.movie.view.fragment.home.HomeAdapter
import com.example.movie.view.fragment.home.HomeViewModel
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton

@AndroidEntryPoint
class MovieFragment : BaseFragment<FragmentMovieBinding>() {

    private val viewModel: HomeViewModel by viewModels()
    var page = 1

    private var testAdapter = HomeAdapter()

    override val bindingInflater: (LayoutInflater) -> FragmentMovieBinding
        get() = FragmentMovieBinding::inflate

    override fun initialization() {
        setupView()
//        setupRecyclerView()

    }

    override fun observeViewModel() {
        viewModel.getNowPlaying(page)
        viewModel.nowPlaying.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
//                        setupAdapter(data)
                        setupNowPlaying(data)
//                        view?.hideSkeleton()
                    } else
                        Log.e("rvKosong", "getMovie: $data")
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
//        binding.root.loadSkeleton {
//            val customShimmer = Shimmer.AlphaHighlightBuilder()
//                .setDirection(Shimmer.Direction.TOP_TO_BOTTOM)
//                .build()
//            shimmer(customShimmer)
//        }
        binding.layoutPopular.txtCategory.text = getString(R.string.movie_category_popular)
        binding.layoutTopRated.txtCategory.text = getString(R.string.movie_category_toprated)
        binding.layoutNowPlaying.txtCategory.text = getString(R.string.movie_category_nowplaying)
    }

//    private fun setupAdapter(list: ArrayList<ResultsItem>?) {
//        testAdapter.apply {
//            setNewInstance(list?.toMutableList())
//            setOnItemClickListener { _, _, position ->
//                val item = list?.get(position)
//                val intent = data.let {
//                    DetailMovieActivity.newIntent(context, item?.id)
//                }
//                startActivity(intent)
//            }
//        }
//
//    }
//
//    private fun setupRecyclerView() {
//        binding.layoutNowPlaying.apply {
//            shimmerPoster.root.visibility = GONE
//            rvPoster.apply {
//                visibility = VISIBLE
//                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//                adapter = testAdapter
//            }
//        }
//    }

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