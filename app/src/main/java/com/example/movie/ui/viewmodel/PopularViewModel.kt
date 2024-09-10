package com.example.movie.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movie.data.models.ResultXXX
import com.example.movie.data.repository.PopularMovieRepository

class PopularViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = PopularMovieRepository()  // No need for Application context
    val populardMovies: LiveData<List<ResultXXX>> = repository.getPopularMovies()

    init {
        // Optionally trigger data loading here if needed
    }
}
