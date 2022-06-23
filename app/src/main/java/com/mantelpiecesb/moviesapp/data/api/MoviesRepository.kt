package com.mantelpiecesb.moviesapp.data.api

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow
import com.mantelpiecesb.moviesapp.data.db.MovieDao
import com.mantelpiecesb.moviesapp.models.Movie
import com.mantelpiecesb.moviesapp.utils.Constants
import javax.inject.Inject

class MoviesRepository @Inject constructor(
    private val moviesService: MoviesService,
    private val movieDao: MovieDao
    ) {

    suspend fun getSearchResultStream(context: Context): Flow<PagingData<Movie>> {

        val pagingSourceFactory = movieDao.getAllMovies()
        val connectivityManager =
            ContextCompat.getSystemService(context, ConnectivityManager::class.java)
        val activeNetwork: NetworkInfo? = connectivityManager?.activeNetworkInfo
        val isConnected: Boolean = activeNetwork?.isConnectedOrConnecting == true

        if (isConnected) {
            movieDao.deleteAll()
            return Pager(
                config = PagingConfig(
                    enablePlaceholders = false,
                    pageSize = Constants.NETWORK_PAGE_SIZE
                ),
                pagingSourceFactory = { MoviesPagingSource(moviesService, movieDao) }
            ).flow
        } else {
            Toast.makeText(
                context,
                "Похоже, нет подключения к Интернету. Показаны последние загруженные данные",
                Toast.LENGTH_LONG
            ).show()
            return Pager(
                config = PagingConfig(enablePlaceholders = false, pageSize = 20),
                pagingSourceFactory = { pagingSourceFactory }
            ).flow
        }
    }

}

