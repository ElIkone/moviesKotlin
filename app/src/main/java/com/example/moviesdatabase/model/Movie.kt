package com.example.moviesdatabase.model
import androidx.room.Entity

@Entity(tableName = "movie")
data class Movie (
val id: Int,
val title: String,
val poster_path: String,
var url_image: String
)