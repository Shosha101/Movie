package com.example.movie.ui.viewmodel
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.MovieWatched
import com.example.movie.data.repository.WatchListMovieRepository
import kotlinx.coroutines.launch

class WatchlistViewModel(private val repository: WatchListMovieRepository) : ViewModel() {

    private val _movies = MutableLiveData<List<MovieWatched>>()
    val movies: LiveData<List<MovieWatched>> = _movies

    // Function to fetch all saved movies and update LiveData
    fun displayMovies() {
        viewModelScope.launch {
            try {
                val movieList = repository.getAllMovies()
                Log.d("WatchlistViewModel", "Fetched movies: $movieList")
                _movies.value = movieList
            } catch (e: Exception) {
                Log.e("WatchlistViewModel", "Error fetching movies: $e")
            }
        }
    }

    // Save movie to the database
    fun saveMovie(movie: MovieWatched) {
        viewModelScope.launch {
            try {
                repository.saveMovie(movie)
                // Log movie details or use debug to ensure it's correctly inserted
                Log.d("WatchlistViewModel", "Movie saved: $movie")
            } catch (e: Exception) {
                Log.e("WatchlistViewModel", "Error saving movie: $e")
            }
        }
    }

}
