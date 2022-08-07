package com.example.movie.view.activity.detailpeople

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View.VISIBLE
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import coil.load
import com.example.movie.R
import com.example.movie.R.string.*
import com.example.movie.base.BaseActivity
import com.example.movie.base.NetworkResult
import com.example.movie.databinding.ActivityDetailPeopleBinding
import com.example.movie.remote.response.ItemCast
import com.example.movie.remote.response.ItemCrew
import com.example.movie.remote.response.ResponseDetailPeople
import com.example.movie.utils.Constants.BASE_URL_POSTER
import com.example.movie.view.activity.detail.DetailMovieActivity
import com.facebook.shimmer.Shimmer
import dagger.hilt.android.AndroidEntryPoint
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton

@AndroidEntryPoint
class DetailPeopleActivity : BaseActivity<ActivityDetailPeopleBinding>() {

    private var id: Int? = 0

    private val viewModel: DetailPeopleViewModel by viewModels()

    override fun setLayout(inflater: LayoutInflater): ActivityDetailPeopleBinding {
        return ActivityDetailPeopleBinding.inflate(inflater)
    }

    override fun initialization() {
        getData()

    }

    private fun getData() {
        intent.let {
            id = it.getIntExtra("ID", 0)
        }
    }

    override fun observeViewModel() {
        viewModel.getPersonDetail(id)
        viewModel.detailperson.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val data = response.data
                    setupView(data)
                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }


        viewModel.getPersonMovieList(id)
        viewModel.personmovieList.observe(this) { response ->
            when (response) {
                is NetworkResult.Success -> {
                    val dataCrew = response.data?.crew
                    if (!dataCrew.isNullOrEmpty()) {
                        binding.layoutCrew.root.visibility = VISIBLE
                        setupCrew(dataCrew)
                    }

                    val dataCast = response.data?.cast
                    if (!dataCast.isNullOrEmpty()) {
                        binding.layoutCast.root.visibility = VISIBLE
                        setupCast(dataCast)
                    }

                }
                is NetworkResult.Error -> {

                }
                is NetworkResult.Loading -> {

                }
            }
        }

    }

    private fun setupView(data: ResponseDetailPeople?) {
        binding.run {
            root.hideSkeleton()
            imgPhoto.load(BASE_URL_POSTER + data?.profilePath)
            txtName.text = data?.name
            txtBiography.text = data?.biography
            txtJob.text = getString(txt_job, data?.knownForDepartment)
            if (!data?.birthday.isNullOrEmpty()) {
                txtBirthday.text = getString(txt_birthday, data?.birthday)
            } else txtBirthday.text = getString(txt_birthday, "Tidak Diketahui")

            if (!data?.placeOfBirth.isNullOrEmpty()) {
                txtPlaceBirth.text = getString(txt_placebirthday, data?.placeOfBirth)
            } else txtPlaceBirth.text = getString(txt_placebirthday, "Tidak diketahui")

        }
    }

    private fun setupCast(list: ArrayList<ItemCast>?) {
        val castAdapter = PersonCastAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = DetailMovieActivity.newIntent(context, item?.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }


        }
        binding.layoutCast.apply {
            txtTitle.text = getString(title_actor_films)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = castAdapter
            }
        }
    }

    private fun setupCrew(list: ArrayList<ItemCrew>?) {
        val crewAdapter = PersonCrewAdapter().apply {
            setNewInstance(list?.toMutableList())
            setOnItemClickListener { _, _, position ->
                val item = list?.get(position)
                val intent = DetailMovieActivity.newIntent(context, item?.id)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
        binding.layoutCrew.apply {
            txtTitle.text = getString(title_crew_films)
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                adapter = crewAdapter
            }
        }
    }


    companion object {
        fun newIntent(context: Context?, id: Int?): Intent {
            val intent = Intent(context, DetailPeopleActivity::class.java).apply {
                putExtra("ID", id)
            }
            return intent
        }
    }



}