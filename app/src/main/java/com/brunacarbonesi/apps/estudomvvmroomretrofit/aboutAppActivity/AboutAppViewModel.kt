package com.brunacarbonesi.apps.estudomvvmroomretrofit.aboutAppActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AboutAppViewModel: ViewModel() {

    private val _liveDataAboutApp = MutableLiveData<String>()
    val liveDataAboutApp: LiveData<String> get() = _liveDataAboutApp

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AboutAppViewModel() as T
        }
    }
}