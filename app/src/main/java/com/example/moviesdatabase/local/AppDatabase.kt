package com.example.moviesdatabase.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.moviesdatabase.model.Movie

@Database(entities = [Movie::class], version =1 , exportSchema = false)
abstract class AppDatabase:RoomDatabase() {

}