package com.example.moviesdatabase.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.moviesdatabase.network.MainRepository
import com.example.moviesdatabase.utils.Resource
import kotlinx.coroutines.Dispatchers

class MainViewModel(private val apiHelper: MainRepository) : ViewModel() {
    fun getUsers() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = apiHelper.getMovies()))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}