package com.example.movie.sources

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.remote.response.ItemPeople
import com.example.movie.remote.response.ResponsePeople
import com.example.movie.services.PeopleServinceInstance
import retrofit2.http.Query
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleDataSource @Inject constructor(
    private val service: PeopleServinceInstance
) {

    suspend fun getPersonDetail(person_id: Int?) = service.getPersonDetail(person_id)

    suspend fun getPersonMovieCredits(person_id: Int?) = service.getPersonMovieCredits(person_id)

    suspend fun getPopularPeople(page: Int?) = service.getPopularPeople(page)


}