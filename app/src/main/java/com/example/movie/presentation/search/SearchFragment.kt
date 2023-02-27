package com.example.movie.presentation.search


import android.view.LayoutInflater
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.presentation.core.BaseFragment
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.databinding.FragmentSearchBinding
import com.example.movie.helper.showToast
import com.example.movie.data.remote.response.ResultsItem
import com.example.movie.presentation.detailmovie.DetailMovieActivity
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception


@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>() {

    private val viewModel: SearchViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): FragmentSearchBinding {
        return FragmentSearchBinding.inflate(inflater)
    }

    override fun initialization() {
        searchPoster()
    }

    override fun observeViewModel() {
        viewModel.searchMovie.observe(viewLifecycleOwner) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        setupView(data)
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

    private fun searchPoster() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                if (p0.toString().trim() != "") {
                    try {
                        viewModel.getSearchMovie(p0.toString(), page = 1)
                    } catch (e: Exception) {
                        e.message
                    }
                }
                return false
            }

        })
    }

    private fun setupView(list: ArrayList<ResultsItem>) {
        val searchAdapter = SearchAdapter().apply {
            setNewInstance(list.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list.get(position)
                val intent = data.let {
                    DetailMovieActivity.newIntent(context, item?.id)
                }
                startActivity(intent)
            }
        }
        binding.rvSearch.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = searchAdapter
        }
    }


}