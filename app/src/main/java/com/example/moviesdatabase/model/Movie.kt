package com.example.moviesdatabase.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey val id: Int,
    var title: String,
    val poster_path: String,
    var url_image: String,
    val release_date: String,
    val overview: String,
    val vote_average: String
)