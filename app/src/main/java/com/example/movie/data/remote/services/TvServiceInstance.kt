package com.example.movie.data.remote.services

import com.example.movie.data.remote.response.ResponseTv
import com.example.movie.external.utils.Constants.URL_AIRINGTODAY_TV
import com.example.movie.external.utils.Constants.URL_ONTHEAIR_TV
import com.example.movie.external.utils.Constants.URL_POPULAR_TV
import com.example.movie.external.utils.Constants.URL_TOPRATED_TV
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface TvServiceInstance {

    @GET(URL_AIRINGTODAY_TV)
    suspend fun getAiringTodayTv(
        @Query("page") page: Int
    ): Response<ResponseTv>

    @GET(URL_TOPRATED_TV)
    suspend fun getTopRatedTv(
        @Query("page") page: Int
    ): Response<ResponseTv>

    @GET(URL_ONTHEAIR_TV)
    suspend fun getOntheAirTv(
        @Query("page") page: Int
    ): Response<ResponseTv>

    @GET(URL_POPULAR_TV)
    suspend fun getPopularTv(
        @Query("page") page: Int
    ): Response<ResponseTv>

}