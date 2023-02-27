package com.example.movie.data.remote.datasource

import com.example.movie.data.remote.services.PeopleServinceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleDataSource @Inject constructor(
    private val service: PeopleServinceInstance
) {

    suspend fun getPersonDetail(person_id: Int?) = service.getPersonDetail(person_id)

    suspend fun getPersonMovieCredits(person_id: Int?) = service.getPersonMovieCredits(person_id)


}