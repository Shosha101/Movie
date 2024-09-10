package com.example.movie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.ResultXXXX
import com.example.movie.data.repository.MovieRepository
import kotlinx.coroutines.launch

class SearchViewModel(private val movieRepository: MovieRepository) : ViewModel() {

    private val _searchResults = MutableLiveData<List<ResultXXXX>>()
    val searchResults: LiveData<List<ResultXXXX>> get() = _searchResults

    fun searchMovies(query: String) {
        viewModelScope.launch {
            try {
                val results = movieRepository.searchMovies(query)
                _searchResults.value = results
            } catch (e: Exception) {
                // Handle error
                _searchResults.value = emptyList()
            }
        }
    }

    fun clearResults() {
        _searchResults.value = emptyList()
    }
}
