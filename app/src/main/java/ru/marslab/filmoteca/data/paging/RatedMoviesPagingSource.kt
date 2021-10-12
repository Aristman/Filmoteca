package ru.marslab.filmoteca.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import ru.marslab.filmoteca.data.mapper.toDomain
import ru.marslab.filmoteca.data.retrofit.MovieApi
import ru.marslab.filmoteca.domain.model.Movie

open class RatedMoviesPagingSource(
    private val movieApi: MovieApi,
    private val apiKey: String,
    private val language: String?,
    private val region: String?
) :
    PagingSource<Int, Movie>() {

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        return try {
            val loadKey = params.key ?: 1
            val popularMovies = movieApi.getTopRatedMovies(apiKey, language, loadKey, region)
            val responseBody = popularMovies.body()
            if (popularMovies.isSuccessful && responseBody != null) {
                val prevKey = if (loadKey == 1) null else loadKey.minus(1)
                val nextKey = if (loadKey == responseBody.totalPages) null else loadKey.plus(1)
                LoadResult.Page(
                    data = popularMovies.body()!!.toDomain(),
                    prevKey,
                    nextKey
                )
            } else {
                LoadResult.Error(Exception())
            }
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}