package com.brunacarbonesi.apps.estudomvvmroomretrofit.movieslistActivity

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.repository.Repository
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.repository.RepositoryImpl
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewData
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.ViewStatus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MoviesListViewModel(private val repository: Repository): ViewModel() {

    val _liveDataMovie = MutableLiveData<ViewData<List<MovieVO>>>()
    val liveDataMovie: LiveData<ViewData<List<MovieVO>>> get() = _liveDataMovie

    private val compositeDisposable = CompositeDisposable()

    private var value: List<MovieVO>? = null
    private var status: ViewStatus? = null

    fun fillMoviesList(isNetworkAvailable: Boolean) {
        _liveDataMovie.value = ViewData(viewStatus = ViewStatus.LOADING)

        compositeDisposable.add(
            repository.getMovies(isNetworkAvailable)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    {
                        if (it.isEmpty()) {
                            status = ViewStatus.NO_VALUE
                            _liveDataMovie.postValue(ViewData(viewStatus = ViewStatus.NO_VALUE))
                        } else {
                            status = ViewStatus.SUCCESS
                            value = it
                            _liveDataMovie.postValue(ViewData(it, ViewStatus.SUCCESS))
                        }
                    },
                    {
                        status = ViewStatus.ERROR
                        _liveDataMovie.postValue(ViewData(viewStatus = ViewStatus.ERROR))
                    }
                 )
        )
    }

    override fun onCleared() {
        compositeDisposable.clear()
        super.onCleared()
    }

    @Suppress("UNCHECKED_CAST")
    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return MoviesListViewModel(RepositoryImpl()) as T
        }
    }
}