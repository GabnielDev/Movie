package com.example.movie.remote

import com.example.movie.remote.service.PeopleServinceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleDataSource @Inject constructor(
    private val service: PeopleServinceInstance
) {

    suspend fun getPersonDetail(person_id: Int?) = service.getPersonDetail(person_id)

    suspend fun getPersonMovieCredits(person_id: Int?) = service.getPersonMovieCredits(person_id)

}