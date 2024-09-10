package com.example.movie.data.models

import com.squareup.moshi.Json

data class ResultXXXX(
    val adult: Boolean,
    @field:Json(name = "backdrop_path") val backdrop_path: String?,  // Updated to nullable
    val genre_ids: List<Int>,
    val id: Int,
    val original_language: String,
    val original_title: String,
    val overview: String,
    val popularity: Double,
    val poster_path: String,
    val release_date: String,
    val title: String,
    val video: Boolean,
    val vote_average: Double,
    val vote_count: Int
)
