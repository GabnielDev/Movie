package com.example.movie.view.fragment.upcoming

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.ResponseMovie
import com.example.movie.remote.response.ResponseTrailer
import com.example.movie.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UpComingViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _upComing = MutableLiveData<NetworkResult<ResponseMovie>>()
    val upComing: LiveData<NetworkResult<ResponseMovie>> = _upComing

    fun getUpComing(page: Int) = viewModelScope.launch {
        repository.getUpComingMovie(page)
            .collect {
                _upComing.value = it
            }
    }

    private val _trailer = MutableLiveData<NetworkResult<ResponseTrailer>>()
    val trailer: LiveData<NetworkResult<ResponseTrailer>> = _trailer

    fun getTrailer(id: Int?) = viewModelScope.launch {
        repository.getTrailerMovie(id)
            .collect {
                _trailer.value = it
            }
    }

}