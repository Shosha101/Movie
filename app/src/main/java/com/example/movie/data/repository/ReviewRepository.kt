package com.example.movie.data.repository
import com.example.movie.data.models.Result
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient

class ReviewRepository {

    private val apiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)
    }

    suspend fun getReviews(movieId: Int): List<Result> {
        val response = apiService.getMovieReviews(movieId)
        return response.results
    }
}
