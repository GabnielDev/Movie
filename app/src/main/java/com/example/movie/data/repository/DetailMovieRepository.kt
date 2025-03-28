package com.example.movie.data.repository

import com.example.movie.data.remote.core.BaseApiResponse
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.data.remote.datasource.MovieDetailDataSource
import com.example.movie.data.remote.response.ResponseCredits
import com.example.movie.data.remote.response.ResponseDetailMovie
import com.example.movie.data.remote.response.ResponseTrailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DetailMovieRepository @Inject constructor(
    private val dataSource: MovieDetailDataSource
) : BaseApiResponse() {

    suspend fun getDetailMovie(movie_id: Int?): Flow<NetworkResult<ResponseDetailMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getDetailMovie(movie_id)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrailerMovie(movie_id: Int?): Flow<NetworkResult<ResponseTrailer>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getTrailerMovie(movie_id)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getCredits(movie_id: Int?): Flow<NetworkResult<ResponseCredits>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getCredits(movie_id)
            })
        }.flowOn(Dispatchers.IO)
    }

}