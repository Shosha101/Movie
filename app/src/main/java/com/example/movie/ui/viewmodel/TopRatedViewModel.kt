package com.example.movie.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movie.data.models.ResultsOfTopRated
import com.example.movie.data.repository.TopRatedMovieRepository

class TopRatedViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = TopRatedMovieRepository()  // No need for Application context
    val topRatedMovies: LiveData<List<ResultsOfTopRated>> = repository.getTopRatedMovies()

    init {
        // Optionally trigger data loading here if needed
    }
}
