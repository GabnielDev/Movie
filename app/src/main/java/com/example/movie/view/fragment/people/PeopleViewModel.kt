package com.example.movie.view.fragment.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.ResponseDetailPeople
import com.example.movie.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

//    private val _detailperson = MutableLiveData<NetworkResult<ResponseDetailPeople>>()
//    val detailperson: LiveData<NetworkResult<ResponseDetailPeople>> = _detailperson
//
//    fun getPersonDetail(person_id: Int?) = viewModelScope.launch {
//        repository.getPersonDetail(person_id)
//            .collect {
//                _detailperson.value = it
//            }
//    }

}