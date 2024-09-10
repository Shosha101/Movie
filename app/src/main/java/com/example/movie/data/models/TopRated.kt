package com.example.movie.data.models
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)

data class TopRated(
    val page: Int,
    val results: List<ResultsOfTopRated>,
    val total_pages: Int,
    val total_results: Int
)