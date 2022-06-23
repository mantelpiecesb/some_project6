package com.mantelpiecesb.moviesapp.models

data class MoviesResponse(
    val results: List<Movie>,
    val has_more: Boolean
)