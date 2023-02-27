package com.example.movie.presentation.upcoming

import android.view.LayoutInflater
import android.view.View.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.presentation.core.BaseFragment
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.databinding.FragmentUpComingBinding
import com.example.movie.helper.gotoYoutube
import com.example.movie.helper.showToast
import com.example.movie.data.remote.response.ResultsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpComingBinding>() {

    private val viewModel: UpComingViewModel by viewModels()

    var page = 1
    private var key: String? = null

    private var upComingAdapter = UpComingAdapter()

    override fun setLayout(inflater: LayoutInflater): FragmentUpComingBinding {
        return FragmentUpComingBinding.inflate(inflater)
    }


    override fun initialization() {
        setupRecyclerView()
    }

    override fun observeViewModel() {
        viewModel.getUpComing(page)
        viewModel.upComing.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupAdapter(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.shimmerUpComing.root.startShimmer()
                }
            }
        }

        viewModel.trailer.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results?.get(0)?.key
                    if (!data.isNullOrEmpty()) {
                        key = data
                        gotoYoutube(context, key)
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

    private fun setupAdapter(list: ArrayList<ResultsItem>?) {
        upComingAdapter.apply {
            setNewInstance(list?.toMutableList())
            addChildClickViewIds(R.id.txtTontonSekarang)
            setOnItemChildClickListener { _, _, position ->
                val item = list?.get(position)
                val id = item?.id
                viewModel.getTrailer(id)
            }
        }
    }

    private fun setupRecyclerView() {
        binding.rvUpComing.apply {
            visibility = VISIBLE
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = upComingAdapter
        }
        binding.shimmerUpComing.root.visibility = GONE
    }


}





