package com.example.movie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.Crew
import com.example.movie.data.models.Result
import com.example.movie.data.repository.CastRepository

import com.example.movie.data.repository.ReviewRepository
import kotlinx.coroutines.launch

class CastViewModel(private val repository: CastRepository) : ViewModel() {

    private val _reviews = MutableLiveData<List<Crew>>()
    val cast: LiveData<List<Crew>> = _reviews

    fun loadCast(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getCast(movieId)
            _reviews.postValue(result)
        }
    }
}
