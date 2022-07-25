package com.example.movie.view.fragment.upcoming

import android.view.LayoutInflater
import android.view.View.*
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentUpComingBinding
import com.example.movie.helper.showToast
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpComingBinding>() {

    private val viewModel: UpComingViewModel by viewModels()

    private val upComingAdapter = UpComingAdapter()

    var page = 1

    override val bindingInflater: (LayoutInflater) -> FragmentUpComingBinding
        get() = FragmentUpComingBinding::inflate

    override fun initialization() {
        setupView()
    }

    override fun observeViewModel() {
        viewModel.getUpComing(page)
        viewModel.upComing.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        upComingAdapter.addData(data)
                        binding.shimmerUpComing.root.visibility = GONE
                        binding.rvUpComing.visibility = VISIBLE
                        upComingAdapter.apply {
//                            addChildClickViewIds(R.id.txtTontonSekarang)
//                            setOnItemClickListener { _, _, position ->
//                                Toast.makeText(view?.context, "Test Tonton", Toast.LENGTH_SHORT)
//                                    .show()
//                            }
//                        }
                        }
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
    }

    private fun setupView() {
        binding.rvUpComing.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = upComingAdapter
        }
    }

}





