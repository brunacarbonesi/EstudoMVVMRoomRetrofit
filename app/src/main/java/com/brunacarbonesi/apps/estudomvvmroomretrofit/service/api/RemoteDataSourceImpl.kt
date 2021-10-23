package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.AppConstants
import io.reactivex.Single

class RemoteDataSourceImpl(private val serverApi: MovieService) :
    RemoteDataSource {
    override fun getMovies(): Single<List<MovieVO>> =
        serverApi.getPopularMovies(AppConstants.API_KEY, 1).map {
            MovieRemoteMapper.mapRemote(it.movies)
        }
}