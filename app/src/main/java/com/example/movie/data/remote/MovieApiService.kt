package com.example.movie.data.remote

import com.example.movie.data.models.Cast
import com.example.movie.data.models.MovieDetailsById
import com.example.movie.data.models.NowPlaying
import com.example.movie.data.models.Popular
import com.example.movie.data.models.Review
import com.example.movie.data.models.Search
import com.example.movie.data.models.TopRated
import com.example.movie.data.models.Upcoming
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/{movieId}")
    suspend fun getMovieDetails(@Path("movieId") movieId: Int): MovieDetailsById

    @GET("movie/{movieId}/reviews")
    suspend fun getMovieReviews(@Path("movieId") movieId: Int): Review

    @GET("movie/{movieId}/credits")
    suspend fun getMovieCast(@Path("movieId") movieId: Int): Cast

    @GET("movie/top_rated")
    suspend fun getTopRated(): TopRated

    @GET("movie/upcoming")
    suspend fun getUpcoming(): Upcoming

    @GET("movie/now_playing")
    suspend fun getNowPlaying(): NowPlaying

    @GET("movie/popular")
    suspend fun getPopular(): Popular

    @GET("search/movie")
    suspend fun searchMovies(@Query("query") query: String): Search

}
