package com.example.movie.sources

import com.example.movie.services.SearchServiceInstace
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SearchDataSource @Inject constructor(
    private val service: SearchServiceInstace
) {

    suspend fun getSearchMovie(query: String, page: Int) = service.getSearchMovie(query, page)

}