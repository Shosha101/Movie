package com.example.movie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.Result

import com.example.movie.data.repository.ReviewRepository
import kotlinx.coroutines.launch

class ReviewViewModel(private val repository: ReviewRepository) : ViewModel() {

    private val _reviews = MutableLiveData<List<Result>>()
    val reviews: LiveData<List<Result>> = _reviews

    fun loadReviews(movieId: Int) {
        viewModelScope.launch {
            val result = repository.getReviews(movieId)
            _reviews.postValue(result)
        }
    }
}
