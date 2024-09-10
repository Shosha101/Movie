package com.example.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.models.ResultX
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class UpcomingMoviesRepository() {

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)  // Pass context here
    }

    fun getUpcoming(): LiveData<List<ResultX>> {
        val upComingMovies = MutableLiveData<List<ResultX>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getUpcoming()
                withContext(Dispatchers.Main) {
                    upComingMovies.value = response.results
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    upComingMovies.value = emptyList()
                }
            }
        }
        return upComingMovies
    }
}
