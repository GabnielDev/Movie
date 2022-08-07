package com.example.movie.services

import com.example.movie.remote.response.ResponseMovie
import com.example.movie.remote.response.ResultsItem
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