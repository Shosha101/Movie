package com.example.movie.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movie.data.models.ResultX

import com.example.movie.data.repository.UpcomingMoviesRepository

class UpComingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = UpcomingMoviesRepository()  // No need for Application context
    val upComingdMovies: LiveData<List<ResultX>> = repository.getUpcoming()

    init {
        // Optionally trigger data loading here if needed
    }
}
