package com.example.movie.data.remote.services

import com.example.movie.data.remote.response.ResponseMovie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchServiceInstace {

    @GET("search/movie")
    suspend fun getSearchMovie(
        @Query("query") query: String,
        @Query("page") page: Int
    ): Response<ResponseMovie>

}