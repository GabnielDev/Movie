package com.example.movie.data.repository

import com.example.movie.data.remote.core.BaseApiResponse
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.data.remote.response.ResponseMovie
import com.example.movie.data.remote.datasource.MovieDataSource
import com.example.movie.data.remote.response.ResponseTrailer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(
    private val movieDataSource: MovieDataSource
) : BaseApiResponse() {

    suspend fun getNowPlayingMovie(page: Int): Flow<NetworkResult<ResponseMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                movieDataSource.getNowPlayingMovie(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedMovie(page: Int): Flow<NetworkResult<ResponseMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                movieDataSource.getTopRatedMovie(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getUpComingMovie(page: Int) : Flow<NetworkResult<ResponseMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                movieDataSource.getUpComingMovie(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopularMovie(page: Int): Flow<NetworkResult<ResponseMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                movieDataSource.getPopularMovie(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTrailerMovie(id: Int?): Flow<NetworkResult<ResponseTrailer>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                movieDataSource.getTrailerMovie(id)
            })
        }.flowOn(Dispatchers.IO)
    }

}