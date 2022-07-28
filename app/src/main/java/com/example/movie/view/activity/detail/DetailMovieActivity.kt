package com.example.movie.view.activity.detail

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movie.BuildConfig
import com.example.movie.base.BaseActivity
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.ActivityDetailMovieBinding
import com.example.movie.helper.gotoWhatsApp
import com.example.movie.helper.gotoYoutube
import com.example.movie.helper.showToast
import com.example.movie.remote.response.GenresItem
import com.example.movie.remote.response.ResponseDetailMovie
import com.example.movie.utils.Constants.BASE_URL_BACKPOSTER
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    var id: Int? = null
    private var key: String? = null

    private val viewModel: DetailMovieViewModel by viewModels()

    override val setLayout: (LayoutInflater) -> ActivityDetailMovieBinding
        get() = ActivityDetailMovieBinding::inflate

    override fun initialization() {
        getData()
        setonclickListener()
    }

    private fun getData() {
        intent.let {
            id = it.getIntExtra(ID, 0)
        }
    }

    override fun observeViewModel() {
        viewModel.getDetailMovie(id)
        viewModel.detailMovie.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data
                    setupView(data)
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(this, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.shimmerDetail.root.startShimmerAnimation()
                }
            }
        }

        viewModel.getDetailGenre(id)
        viewModel.genre.observe(this) { response ->
            if (!response.isNullOrEmpty()) {
                setupGenre(response)
            }

        }

        viewModel.getDetailTrailer(id)
        viewModel.trailer.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results?.get(0)?.key
                    if (!data.isNullOrEmpty()) {
                        key = data
                    }
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(this, it)
                    }
                }
                is NetworkResult.Loading -> {
                }
            }
        }
    }

    private fun setupView(data: ResponseDetailMovie?) {
        binding.shimmerDetail.root.visibility = GONE
        binding.layoutDetail.apply {
            root.visibility = VISIBLE
            imgPoster.load(BASE_URL_BACKPOSTER + data?.backdropPath)
            txtJudulPertama.text = data?.originalTitle
            txtJudul.text = data?.title
            txtDesc.text = data?.overview
        }
    }

    private fun setupGenre(list: ArrayList<GenresItem>?) {
        val genreAdapter = GenreDetailAdapter().apply {
            setNewInstance(list?.toMutableList())
        }
        binding.layoutDetail.apply {
            rvGenre.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = genreAdapter
            }
        }

    }

    private fun setonclickListener() {
        binding.layoutDetail.apply {
            txtTontonSekarang.setOnClickListener {
                gotoYoutube(this@DetailMovieActivity, key)
            }
            imgShare.setOnClickListener {
                gotoWhatsApp(
                    this@DetailMovieActivity,
                    "Tonton Trailernya disini\n\nhttp://www.youtube.com/watch?v=$key"
                )
            }
        }
    }


    companion object {

        private const val ID = BuildConfig.APPLICATION_ID + ".DETAIL.MOVIE.ID"

        fun newIntent(context: Context, id: Int?): Intent {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra(ID, id)
            }
            return intent
        }
    }


}