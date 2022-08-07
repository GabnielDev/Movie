package com.example.movie.view.fragment.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movie.base.NetworkResult
import com.example.movie.remote.response.ResponseDetailPeople
import com.example.movie.remote.response.ResponsePeople
import com.example.movie.repository.PeopleRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleViewModel @Inject constructor(
    private val repository: PeopleRepository
) : ViewModel() {

    fun getPopularPeople(page: Int?) =
        repository.getPopularPeople(page).cachedIn(viewModelScope)


}