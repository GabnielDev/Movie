package com.example.movie.view.fragment.upcoming

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movie.R
import com.example.movie.base.BaseFragment
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.FragmentUpComingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpComingBinding>() {

    private val viewModel: UpComingViewModel by viewModels()

    private val upComingAdapter = UpComingAdapter()

    var page = 1

    override val bindingInflater: (LayoutInflater) -> FragmentUpComingBinding
        get() = FragmentUpComingBinding::inflate

    override fun onViewBindingCreated(savedInstanceState: Bundle?) {
        setupView()
        setupAdapter()
    }

    override fun observeViewModel() {
        viewModel.getUpComing(page)
        viewModel.upComing.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        upComingAdapter.addData(data)
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
            }


        }
    }

    override fun getDataFromIntent() {
//        TODO("Not yet implemented")
    }

    private fun setupAdapter() {

    }

    private fun setupView() {
        binding?.rvUpComing?.apply {
            layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = upComingAdapter
        }
    }

}





