package com.example.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.models.ResultXXX
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class PopularMovieRepository() {

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)  // Pass context here
    }

    fun getPopularMovies(): LiveData<List<ResultXXX>> {
        val popularMovies = MutableLiveData<List<ResultXXX>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getPopular()
                withContext(Dispatchers.Main) {
                    popularMovies.value = response.results
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    popularMovies.value = emptyList()
                }
            }
        }
        return popularMovies
    }
}
