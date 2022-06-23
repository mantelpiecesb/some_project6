package com.mantelpiecesb.moviesapp.data.api

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mantelpiecesb.moviesapp.data.db.MovieDao
import com.mantelpiecesb.moviesapp.models.Movie
import javax.inject.Inject


private const val STARTING_PAGE_INDEX = 0

class MoviesPagingSource @Inject constructor(
    private val service: MoviesService,
    private val movieDao: MovieDao
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val offset = params.key ?: STARTING_PAGE_INDEX
        return try {
            val response = service.getMovies(offset = offset)
            val movies = response.results

            movieDao.insertAll(movies)

            LoadResult.Page(
                data = movies,
                prevKey = if (offset == STARTING_PAGE_INDEX) null else offset - 20,
                nextKey = if (!response.has_more) null else offset + 20
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey
        }
    }
}
