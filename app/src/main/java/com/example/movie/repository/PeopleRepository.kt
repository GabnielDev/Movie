package com.example.movie.repository

import com.example.movie.base.BaseApiResponse
import com.example.movie.base.NetworkResult
import com.example.movie.remote.PeopleDataSource
import com.example.movie.remote.response.ResponseDetailPeople
import com.example.movie.remote.response.ResponsePeopleMovie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    private val dataSource: PeopleDataSource
) : BaseApiResponse() {

    suspend fun getPersonDetail(person_id: Int?): Flow<NetworkResult<ResponseDetailPeople>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getPersonDetail(person_id)
            })
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getPersonMovieCredits(person_id: Int?): Flow<NetworkResult<ResponsePeopleMovie>> {
        return flow {
            emit(NetworkResult.Loading())
            emit(safeApiCall {
                dataSource.getPersonMovieCredits(person_id)
            })
        }.flowOn(Dispatchers.IO)
    }

}