package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import io.reactivex.Single

interface MoviesCacheSource {
    fun getMovies(): Single<List<MovieVO>>
    fun insertMovies(list: List<MovieVO>)
    fun updateMovies(list: List<MovieVO>)
}