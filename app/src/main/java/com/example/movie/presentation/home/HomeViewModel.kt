package com.example.movie.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.data.remote.response.ResponseMovie
import com.example.movie.data.remote.response.ResponseTv
import com.example.movie.data.repository.MovieRepository
import com.example.movie.data.repository.TvRepository
import dagger.hilt.android.lifecycle.HiltViewModel
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

    private val _topratedTv = MutableLiveData<NetworkResult<ResponseTv>>()
    val topratedTv: LiveData<NetworkResult<ResponseTv>> = _topratedTv

    fun getTopRatedTv(page: Int) = viewModelScope.launch {
        tvRepository.getTopRatedTv(page)
            .collect {
                _topratedTv.value = it
            }
    }

    private val _ontheairTv = MutableLiveData<NetworkResult<ResponseTv>>()
    val ontheairTv: LiveData<NetworkResult<ResponseTv>> = _ontheairTv

    fun getOnTheAirTv(page: Int) = viewModelScope.launch {
        tvRepository.getOnTheAir(page)
            .collect {
                _ontheairTv.value = it
            }
    }

    private val _popularTv = MutableLiveData<NetworkResult<ResponseTv>>()
    val popularTv: LiveData<NetworkResult<ResponseTv>> = _popularTv

    fun getPopularTv(page: Int) = viewModelScope.launch {
        tvRepository.getPopular(page)
            .collect {
                _popularTv.value = it
            }
    }


}