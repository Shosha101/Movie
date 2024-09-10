package com.example.movie.data.repository

import com.example.movie.data.models.Crew
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient

class CastRepository {

    private val apiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)
    }

    suspend fun getCast(movieId: Int): List<Crew> {
        val response = apiService.getMovieCast(movieId)
        return response.crew // Now it returns only the crew
    }
}
