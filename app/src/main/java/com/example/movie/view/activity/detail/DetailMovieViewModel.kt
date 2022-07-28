package com.example.movie.view.activity.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.GenresItem
import com.example.movie.remote.response.ResponseDetailMovie
import com.example.movie.remote.response.ResponseTrailer
import com.example.movie.repository.DetailMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailMovieViewModel @Inject constructor(
    private val repository: DetailMovieRepository
) : ViewModel() {

    private val _detailMovie = MutableLiveData<NetworkResult<ResponseDetailMovie>>()
    val detailMovie: LiveData<NetworkResult<ResponseDetailMovie>> = _detailMovie

    fun getDetailMovie(movie_id: Int?) = viewModelScope.launch {
        repository.getDetailMovie(movie_id)
            .collect {
                _detailMovie.value = it
            }
    }

    private val _trailer = MutableLiveData<NetworkResult<ResponseTrailer>>()
    val trailer: LiveData<NetworkResult<ResponseTrailer>> = _trailer

    fun getDetailTrailer(movie_id: Int?) = viewModelScope.launch {
        repository.getTrailerMovie(movie_id)
            .collect {
                _trailer.value = it
            }
    }

    private val _genres = MutableLiveData<ArrayList<GenresItem>?>()
    val genre: LiveData<ArrayList<GenresItem>?> = _genres

    fun getDetailGenre(movie_id: Int?) = viewModelScope.launch {
        repository.getDetailMovie(movie_id)
            .collect {
                _genres.value = it.data?.genres
            }
    }

}