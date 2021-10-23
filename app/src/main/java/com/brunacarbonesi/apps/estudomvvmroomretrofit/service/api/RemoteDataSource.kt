package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import io.reactivex.Single

interface RemoteDataSource {
    fun getMovies(): Single<List<MovieVO>>
}