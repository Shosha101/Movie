package com.example.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.models.MovieDetailsById
import com.example.movie.data.remote.MovieApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class DetailsMovieRepository(private val movieApiService: MovieApiService) {

    fun getMovieDetails(movieId: Int): LiveData<MovieDetailsById?> {
        val movieDetails = MutableLiveData<MovieDetailsById?>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getMovieDetails(movieId)
                withContext(Dispatchers.Main) {
                    movieDetails.value = response
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    movieDetails.value = null
                }
            }
        }
        return movieDetails
    }
}
