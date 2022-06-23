package com.mantelpiecesb.moviesapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import com.mantelpiecesb.moviesapp.models.MoviesResponse
import com.mantelpiecesb.moviesapp.utils.Constants.Companion.API_KEY

interface MoviesService {

    @GET("/svc/movies/v2/reviews/all.json")
    suspend fun getMovies(
        @Query("api-key") apiKey: String = API_KEY,
        @Query("offset") offset: Int = 0
    ) : MoviesResponse

}
