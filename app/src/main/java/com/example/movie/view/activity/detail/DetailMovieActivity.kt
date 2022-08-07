package com.example.movie.view.activity.detail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movie.R.string.title_actor_films
import com.example.movie.R.string.title_crew_films
import com.example.movie.adapter.CastAdapter
import com.example.movie.adapter.CrewAdapter
import com.example.movie.base.BaseActivity
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.ActivityDetailMovieBinding
import com.example.movie.helper.gotoWhatsApp
import com.example.movie.helper.gotoYoutube
import com.example.movie.helper.showToast
import com.example.movie.remote.response.*
import com.example.movie.utils.Constants.BASE_URL_BACKPOSTER
import com.example.movie.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.activity.detailpeople.DetailPeopleActivity
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton


@AndroidEntryPoint
class DetailMovieActivity : BaseActivity<ActivityDetailMovieBinding>() {

    private var id: Int? = 0

    private var key: String? = null
    private val viewModel: DetailMovieViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): ActivityDetailMovieBinding {
        return ActivityDetailMovieBinding.inflate(inflater)
    }

    override fun initialization() {
        getData()
        setonclickListener()
    }

    private fun getData() {
        intent.let {
            id = it.getIntExtra("ID", 0)
        }
    }

    override fun observeViewModel() {
        viewModel.getDetailMovie(id)
        viewModel.detailMovie.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data
                    setupView(data)
                    setupGenre(data?.genres)
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(this, it)
                    }
                }
                is NetworkResult.Loading -> {
                    binding.shimmerDetail.root.startShimmer()
                }
            }
        }

//        viewModel.getDetailTrailer(id)
        viewModel.trailer.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data?.results
                    if (!data.isNullOrEmpty()) {
                        key = data[0].key
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

        viewModel.getCredits(id)
        viewModel.credits.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data
                    setupCast(data?.cast)
                    setupCrew(data?.crew)
                }
                is NetworkResult.Error -> {
                    response.message?.getContentIfNotHandled()?.let {
                        showToast(applicationContext, it)
                    }

                }
                is NetworkResult.Loading -> {
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun setupView(data: ResponseDetailMovie?) {
        binding.shimmerDetail.root.visibility = GONE
        binding.layoutDetail.apply {
            root.visibility = VISIBLE
            imgPoster.load(BASE_URL_POSTER + data?.posterPath)
            imgBackposter.load(BASE_URL_BACKPOSTER + data?.backdropPath)
            txtJudulPertama.text = data?.title
            txtRating.text = "Rating : ${data?.voteAverage.toString()}/10"
            txtAdult.text = "Adult : ${data?.adult}"
            txtDesc.text = data?.overview

            if (data?.voteCount!! >= 1000) {
                txtVotes.text =
                    "Votes : " + ("%.2f".format(data.voteCount.toFloat().div(1000))) + "k"
            } else txtVotes.text = "Votes : ${data.voteCount}"

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

    private fun setupCast(list: ArrayList<CastItem>?) {
        val castAdapter = CastAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = DetailPeopleActivity.newIntent(applicationContext, item?.id)
                startActivity(intent)
            }
        }
        binding.layoutDetail.layoutCast.apply {
            txtTitle.text = getString(title_actor_films)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
            }
        }
    }

    private fun setupCrew(list: ArrayList<CrewItem>?) {
        val crewAdapter = CrewAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = DetailPeopleActivity.newIntent(applicationContext, item?.id)
                startActivity(intent)
            }
        }
        binding.layoutDetail.layoutCrew.apply {
            txtTitle.text = getString(title_crew_films)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = crewAdapter
            }
        }

    }

    private fun setonclickListener() {
        binding.layoutDetail.apply {
            btnTontonTrailer.setOnClickListener {
                if (!key.isNullOrEmpty()) {
                    gotoYoutube(applicationContext, key)
                } else
                    showToast(applicationContext, "Trailer Tidak Tersedia")
            }
            imgShare.setOnClickListener {
                gotoWhatsApp(
                    applicationContext,
                    "Tonton Trailernya disini\n\nhttp://www.youtube.com/watch?v=$key"
                )
            }
        }
    }

    companion object {
        fun newIntent(context: Context?, id: Int?): Intent {
            val intent = Intent(context, DetailMovieActivity::class.java).apply {
                putExtra("ID", id)
            }
            return intent
        }
    }


}