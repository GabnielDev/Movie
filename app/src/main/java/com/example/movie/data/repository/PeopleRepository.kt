package com.example.movie.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.movie.data.remote.core.BaseApiResponse
import com.example.movie.data.remote.core.NetworkResult
import com.example.movie.external.paging.PeoplePagingSource
import com.example.movie.data.remote.response.ItemPeople
import com.example.movie.data.remote.datasource.PeopleDataSource
import com.example.movie.data.remote.response.ResponseDetailPeople
import com.example.movie.data.remote.response.ResponsePeopleMovie
import com.example.movie.data.remote.services.PeopleServinceInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class PeopleRepository @Inject constructor(
    private val dataSource: PeopleDataSource,
    private val service: PeopleServinceInstance
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

//    suspend fun getPopularPeople(page: Int?): Flow<NetworkResult<ResponsePeople>> {
//        return flow {
//            emit(NetworkResult.Loading())
//            emit(safeApiCall {
//                dataSource.getPopularPeople(page)
//            })
//        }.flowOn(Dispatchers.IO)
//    }

    fun getPopularPeople(page: Int?): Flow<PagingData<ItemPeople>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20,
                enablePlaceholders = false,
                maxSize = 100
            ),
            pagingSourceFactory = {
                PeoplePagingSource(service = service)
            }
        ).flow

    }

}