package com.example.movie.data.repository

import com.example.movie.data.models.ResultXXXX
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient

class MovieRepository {

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)
    }

    suspend fun searchMovies(query: String): List<ResultXXXX> {
        return try {
            val response = movieApiService.searchMovies(query)
            response.results
        } catch (e: Exception) {
            emptyList()
        }
    }
}
