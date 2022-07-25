package com.example.movie.view.fragment.home.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentTvBinding
import com.example.movie.view.fragment.home.HomeViewModel
import com.example.movie.view.fragment.home.TvAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TvFragment : BaseFragment<FragmentTvBinding>() {

    private val viewModel: HomeViewModel by viewModels()

    private val airingtodayAdapter = TvAdapter()

    override val bindingInflater: (LayoutInflater) -> FragmentTvBinding
        get() = FragmentTvBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
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
                    }
                }
            }
        }
    }

    override fun getDataFromIntent() {
//        TODO("Not yet implemented")
    }

    private fun setupView() {
        binding?.layoutAiring?.apply {
            txtCategory.text = "Airing Today"
            rvPoster.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = airingtodayAdapter
            }
        }
    }

}