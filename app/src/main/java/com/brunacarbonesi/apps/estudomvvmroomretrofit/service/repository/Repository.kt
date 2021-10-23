package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.repository

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import io.reactivex.Single

interface Repository {

    fun getMovies(
        verifyAvailableNetwork: Boolean
    ): Single<List<MovieVO>>

}