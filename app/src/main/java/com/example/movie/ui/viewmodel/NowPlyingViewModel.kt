package com.example.movie.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.movie.data.models.ResultXX
import com.example.movie.data.repository.NowPlayingMovieRepository

class NowPlyingViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = NowPlayingMovieRepository()  // No need for Application context
    val nowPlayingMovies: LiveData<List<ResultXX>> = repository.getNowPlayingMovies()

    init {
        // Optionally trigger data loading here if needed
    }
}
