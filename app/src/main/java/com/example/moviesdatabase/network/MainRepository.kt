package com.example.moviesdatabase.network

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getMovies() = apiHelper.getMovies()
}