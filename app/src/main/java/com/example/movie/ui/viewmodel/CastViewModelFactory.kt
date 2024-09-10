package com.example.movie.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movie.data.repository.CastRepository

class CastViewModelFactory : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CastViewModel::class.java)) {
            return CastViewModel(CastRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
