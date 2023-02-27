package com.example.movie.presentation.detailpeople

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.data.remote.response.ResponseDetailPeople
import com.example.movie.data.remote.response.ResponsePeopleMovie
import com.example.movie.data.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailPeopleViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    private val _detailperson = MutableLiveData<NetworkResult<ResponseDetailPeople>>()
    val detailperson: LiveData<NetworkResult<ResponseDetailPeople>> = _detailperson

    fun getPersonDetail(person_id: Int?) = viewModelScope.launch {
        repository.getPersonDetail(person_id)
            .collect {
                _detailperson.value = it
            }
    }

    private val _personmovieList = MutableLiveData<NetworkResult<ResponsePeopleMovie>>()
    val personmovieList: LiveData<NetworkResult<ResponsePeopleMovie>> = _personmovieList

    fun getPersonMovieList(person_id: Int?) = viewModelScope.launch {
        repository.getPersonMovieCredits(person_id)
            .collect {
                _personmovieList.value = it
            }
    }

}