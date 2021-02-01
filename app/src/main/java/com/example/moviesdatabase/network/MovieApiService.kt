package com.example.moviesdatabase.network
import com.example.moviesdatabase.model.MovieList
import retrofit2.http.GET

public interface MovieApiService {
@GET("/3/movie/popular?api_key=1609818c5e2f435330c30dba561e86bc&language=en-US&page=1")
suspend fun retrieveMovies(): MovieList
}