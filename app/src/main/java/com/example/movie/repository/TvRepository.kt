package com.example.movie.repository

import com.example.movie.base.BaseApiResponse
import com.example.movie.base.NetworkResult
import com.example.movie.remote.TvDataSource
import com.example.movie.remote.response.ResponseTv
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvRepository @Inject constructor(
    private val tvDataSource: TvDataSource
) : BaseApiResponse() {

    suspend fun getAiringTodayTv(page: Int): Flow<NetworkResult<ResponseTv>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                tvDataSource.getAiringToday(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getTopRatedTv(page: Int): Flow<NetworkResult<ResponseTv>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                tvDataSource.getTopRated(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getOnTheAir(page: Int): Flow<NetworkResult<ResponseTv>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                tvDataSource.getOnTheAir(page)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPopular(page:Int): Flow<NetworkResult<ResponseTv>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                tvDataSource.getPopular(page)
            })
        }.flowOn(Dispatchers.IO)
    }

}