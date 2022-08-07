package com.example.movie.view.fragment.people

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movie.base.BaseFragment
import com.example.movie.databinding.FragmentPeopleBinding
import com.example.movie.helper.PagingHelper
import com.example.movie.remote.response.ItemPeople
import com.example.movie.view.activity.detail.DetailMovieActivity
import com.example.movie.view.activity.detailpeople.DetailPeopleActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


@AndroidEntryPoint
class PeopleFragment : BaseFragment<FragmentPeopleBinding>(), PagingAdapter.OnClickListener {

    private val viewModel: PeopleViewModel by viewModels()
    var page = 0

    private lateinit var pagingAdapter: PagingAdapter

    override val bindingInflater: (LayoutInflater) -> FragmentPeopleBinding
        get() = FragmentPeopleBinding::inflate

    override fun initialization() {
        setupRecyclerView()
    }

    override fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.getPopularPeople(page).collectLatest {
                pagingAdapter.submitData(it)
            }
        }
    }

    private fun setupRecyclerView() {
        pagingAdapter = PagingAdapter(this@PeopleFragment)
        val paging = PagingHelper(binding.rvPeople).apply {
        }
        binding.rvPeople.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = pagingAdapter
            addOnScrollListener(paging)
        }
    }

    override fun onItemClicked(item: ItemPeople?) {
        val intent = DetailPeopleActivity.newIntent(context, item?.id)
        startActivity(intent)
    }


}