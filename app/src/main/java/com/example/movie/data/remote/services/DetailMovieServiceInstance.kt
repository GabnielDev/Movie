package com.example.movie.data.remote.services

import com.example.movie.data.remote.response.ResponseCredits
import com.example.movie.data.remote.response.ResponseDetailMovie
import com.example.movie.data.remote.response.ResponseTrailer
import com.example.movie.external.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailMovieServiceInstance {

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") movie_id: Int?
    ): Response<ResponseDetailMovie>

    @GET(Constants.URL_TRAILER_MOVIE)
    suspend fun getTrailerMovie(
        @Path("movie_id") movie_id: Int?
    ): Response<ResponseTrailer>

    @GET("movie/{movie_id}/credits")
    suspend fun getCredits(
        @Path("movie_id") movie_id: Int?
    ): Response<ResponseCredits>

}