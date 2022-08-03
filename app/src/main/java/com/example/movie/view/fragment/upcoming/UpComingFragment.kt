package com.example.movie.view.fragment.upcoming

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentUpComingBinding
import com.example.movie.helper.gotoYoutube
import com.example.movie.helper.showToast
import com.example.movie.remote.response.ResultsItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpComingBinding>() {

    private val viewModel: UpComingViewModel by viewModels()

    var page = 1
    var key: String? = null

    override val bindingInflater: (LayoutInflater) -> FragmentUpComingBinding
        get() = FragmentUpComingBinding::inflate

    override fun initialization() {

    }

    override fun observeViewModel() {
        viewModel.getUpComing(page)
        viewModel.upComing.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupUpcomingAdapter(data)
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(view?.context, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.shimmerUpComing.root.startShimmerAnimation()
                }
            }
        }

        viewModel.trailer.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results?.get(0)?.key
                    if (!data.isNullOrEmpty()) {
                        key = data
                        context?.let { gotoYoutube(it, key) }
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

    private fun setupUpcomingAdapter(list: ArrayList<ResultsItem>?) {
        val upComingAdapter = UpComingAdapter().apply {
            setNewInstance(list?.toMutableList())
            addChildClickViewIds(R.id.txtTontonSekarang)

            setOnItemChildClickListener { _, _, position ->
                val item = list?.get(position)
                val id = item?.id
                viewModel.getTrailer(id)

            }
        }
        binding.rvUpComing.apply {
            visibility = VISIBLE
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = upComingAdapter
        }
        binding.shimmerUpComing.root.visibility = GONE
    }


}





