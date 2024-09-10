package com.example.movie.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.movie.data.models.TopRated
import com.example.movie.data.remote.MovieApiService
import com.example.movie.data.remote.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomePageViewModel(application: Application) : AndroidViewModel(application) {
    private var _movies: MutableLiveData<TopRated> = MutableLiveData()
    val movies: LiveData<TopRated> = _movies

    private val movieApiService: MovieApiService by lazy {
        RetrofitClient.createService(MovieApiService::class.java)
    }

    fun getTopRated() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = movieApiService.getTopRated()
                _movies.postValue(result)
            } catch (e: Exception) {
                // Handle the exception if needed
            }
        }
    }
}
