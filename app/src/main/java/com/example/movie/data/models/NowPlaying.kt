package com.example.movie.data.models

data class NowPlaying(
    val dates: DatesX,
    val page: Int,
    val results: List<ResultXX>,
    val total_pages: Int,
    val total_results: Int
)