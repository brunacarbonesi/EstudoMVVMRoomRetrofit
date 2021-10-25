package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieDetailActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MovieDetailViewModel : ViewModel() {

    private val _liveDataMovieDetail = MutableLiveData<String>()
    val liveDataMovieDetail: LiveData<String> get() = _liveDataMovieDetail

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MovieDetailViewModel() as T
        }
    }
}