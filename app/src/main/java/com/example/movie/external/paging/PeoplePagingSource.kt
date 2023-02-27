package com.example.movie.external.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movie.data.remote.response.ItemPeople
import com.example.movie.data.remote.services.PeopleServinceInstance
import retrofit2.HttpException
import java.io.IOException

private const val DEFAULT_INDEX_PAGE = 1

class PeoplePagingSource(
    private val service: PeopleServinceInstance
) : PagingSource<Int, ItemPeople>() {

    override fun getRefreshKey(state: PagingState<Int, ItemPeople>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ItemPeople> {
        val pageIndex = params.key ?: DEFAULT_INDEX_PAGE
        return try {
            val response = service.getPopularPeople(
                page = pageIndex
            )
            val people = response.body()?.results
            val totalData = people?.size
            LoadResult.Page(
                data = people!!,
                prevKey = if (pageIndex == DEFAULT_INDEX_PAGE) null else pageIndex,
                nextKey = if (totalData == 20) pageIndex + 1 else null
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}