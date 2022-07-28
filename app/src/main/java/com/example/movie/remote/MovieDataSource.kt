package com.example.movie.remote

import com.example.movie.remote.service.MovieServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieDataSource @Inject constructor(
    private val service: MovieServiceInstance
) {

    suspend fun getNowPlayingMovie(page: Int) = service.getNowPlayingMovie(page)

    suspend fun getTopRatedMovie(page: Int) = service.getTopRatedMovie(page)

    suspend fun getUpComingMovie(page: Int) = service.getUpComingMovie(page)

    suspend fun getPopularMovie(page: Int) = service.getPopularMovie(page)

    suspend fun getTrailerMovie(id: Int?) = service.getTrailerMovie(id)

}