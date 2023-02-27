package com.example.movie.data.repository

import com.example.movie.data.remote.core.BaseApiResponse
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.data.remote.datasource.SearchDataSource
import com.example.movie.data.remote.response.ResponseMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchRepository @Inject constructor(
    private val dataSource: SearchDataSource
) : BaseApiResponse() {

    suspend fun getSearchMovie(query: String, page: Int): Flow<NetworkResult<ResponseMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getSearchMovie(query, page)
            })
        }.flowOn(Dispatchers.IO)
    }

}