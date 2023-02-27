package com.example.movie.data.remote.datasource

import com.example.movie.data.remote.services.SearchServiceInstace
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor(
    private val service: SearchServiceInstace
) {

    suspend fun getSearchMovie(query: String, page: Int) = service.getSearchMovie(query, page)

}