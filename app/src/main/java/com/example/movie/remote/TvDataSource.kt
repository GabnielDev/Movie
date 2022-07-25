package com.example.movie.remote

import com.example.movie.remote.service.TvServiceInstance
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TvDataSource @Inject constructor(
    private val service: TvServiceInstance
) {

    suspend fun getAiringToday(page: Int) = service.getAiringTodayTv(page)

}