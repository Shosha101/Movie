package com.example.movie.data.models
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieWatched(
    @PrimaryKey val movieId: Int,
    val title: String,
    val category: String,
    val posterPath: String?,
    val rating: Double?,
    val releaseDate: String?
)
