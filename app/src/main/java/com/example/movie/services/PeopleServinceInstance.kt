package com.example.movie.services

import com.example.movie.remote.response.ResponseDetailMovie
import com.example.movie.remote.response.ResponseDetailPeople
import com.example.movie.remote.response.ResponsePeople
import com.example.movie.remote.response.ResponsePeopleMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PeopleServinceInstance {

    @GET("person/{person_id}")
    suspend fun getPersonDetail(
        @Path("person_id") person_id: Int?
    ): Response<ResponseDetailPeople>

    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(
        @Path("person_id") person_id: Int?
    ): Response<ResponsePeopleMovie>

    @GET("person/popular")
    suspend fun getPopularPeople(
        @Query("page") page: Int?
    ): Response<ResponsePeople>

}