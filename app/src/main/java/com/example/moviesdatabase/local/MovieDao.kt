package com.example.moviesdatabase.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.moviesdatabase.model.Movie

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies() : LiveData<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(movie: Movie)
}