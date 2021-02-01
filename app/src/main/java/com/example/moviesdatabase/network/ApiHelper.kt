package com.example.moviesdatabase.network

class ApiHelper(private val movieApiService: MovieApiService) {
    suspend fun getMovies() = movieApiService.retrieveMovies()
}
