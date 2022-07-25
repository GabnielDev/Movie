package com.example.movie.view.fragment.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.ResponseMovie
import com.example.movie.remote.response.ResponseTv
import com.example.movie.repository.MovieRepository
import com.example.movie.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val tvRepository: TvRepository
) : ViewModel() {

    private val _nowPlaying = MutableLiveData<NetworkResult<ResponseMovie>>()
    val nowPlaying: LiveData<NetworkResult<ResponseMovie>> = _nowPlaying

    fun getNowPlaying(page: Int) = viewModelScope.launch {
        movieRepository.getNowPlayingMovie(page)
            .collect {
                _nowPlaying.value = it
            }
    }

    private val _topRated = MutableLiveData<NetworkResult<ResponseMovie>>()
    val topRated: LiveData<NetworkResult<ResponseMovie>> = _topRated

    fun getTopRated(page: Int) = viewModelScope.launch {
        movieRepository.getTopRatedMovie(page)
            .collect {
                _topRated.value = it
            }
    }

    private val _popular = MutableLiveData<NetworkResult<ResponseMovie>>()
    val popular: LiveData<NetworkResult<ResponseMovie>> = _popular

    fun getPopular(page: Int) = viewModelScope.launch {
        movieRepository.getPopularMovie(page)
            .collect {
                _popular.value = it
            }
    }

    private val _airingtoday = MutableLiveData<NetworkResult<ResponseTv>>()
    val airingtoday: LiveData<NetworkResult<ResponseTv>> = _airingtoday

    fun getAiringtoday(page: Int) = viewModelScope.launch {
        tvRepository.getAiringTodayTv(page)
            .collect {
                _airingtoday.value = it
            }
    }


}