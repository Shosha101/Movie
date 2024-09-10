package com.example.movie.data.models

data class Search(
    val page: Int,
    val results: List<ResultXXXX>,
    val total_pages: Int,
    val total_results: Int
)