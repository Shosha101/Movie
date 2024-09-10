package com.example.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.models.ResultsOfTopRated
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class TopRatedMovieRepository() {

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)  // Pass context here
    }

    fun getTopRatedMovies(): LiveData<List<ResultsOfTopRated>> {
        val topRatedMovies = MutableLiveData<List<ResultsOfTopRated>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getTopRated()
                withContext(Dispatchers.Main) {
                    topRatedMovies.value = response.results
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    topRatedMovies.value = emptyList()
                }
            }
        }
        return topRatedMovies
    }
}
