package com.example.movie.data.models

data class Popular(
    val page: Int,
    val results: List<ResultXXX>,
    val total_pages: Int,
    val total_results: Int
)