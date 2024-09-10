package com.example.movie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.MovieDetailsById
import com.example.movie.data.repository.DetailsMovieRepository
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: DetailsMovieRepository) : ViewModel() {

    private val _movie = MutableLiveData<MovieDetailsById?>()
    val movie: LiveData<MovieDetailsById?> get() = _movie

    fun loadMovieDetails(movieId: Int) {
        viewModelScope.launch {
            try {
                val movieDetailsLiveData = repository.getMovieDetails(movieId)
                movieDetailsLiveData.observeForever { response ->
                    _movie.value = response
                }
            } catch (e: Exception) {
                _movie.value = null
            }
        }
    }
}
