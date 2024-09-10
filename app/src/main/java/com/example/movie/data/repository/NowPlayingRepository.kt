package com.example.movie.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movie.data.models.ResultXX
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


class NowPlayingMovieRepository() {

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)  // Pass context here
    }

    fun getNowPlayingMovies(): LiveData<List<ResultXX>> {
        val nowPlayingMovies = MutableLiveData<List<ResultXX>>()
        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = movieApiService.getNowPlaying()
                withContext(Dispatchers.Main) {
                    nowPlayingMovies.value = response.results
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    nowPlayingMovies.value = emptyList()
                }
            }
        }
        return nowPlayingMovies
    }
}
