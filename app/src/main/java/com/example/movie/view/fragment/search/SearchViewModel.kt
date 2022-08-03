package com.example.movie.view.fragment.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.ResponseMovie
import com.example.movie.repository.SearchRepository
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: SearchRepository
) : ViewModel() {

    private val _searchMovie = MutableLiveData<NetworkResult<ResponseMovie>>()
    val searchMovie: LiveData<NetworkResult<ResponseMovie>> = _searchMovie

    fun getSearchMovie(query: String, page: Int) = viewModelScope.launch {
        repository.getSearchMovie(query, page)
            .collect {
                _searchMovie.value = it
            }
    }

}