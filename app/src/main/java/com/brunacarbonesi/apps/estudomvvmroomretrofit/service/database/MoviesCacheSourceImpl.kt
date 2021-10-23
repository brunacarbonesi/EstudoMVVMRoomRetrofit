package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import io.reactivex.Single

class MoviesCacheSourceImpl(private val movieDao: MovieDao): MoviesCacheSource {
    override fun getMovies(): Single<List<MovieVO>> {
        return movieDao.getAllMovies()
            .map { MovieCacheMapper.mapCacheToMovies(it) }
    }

    override fun insertMovies(list: List<MovieVO>) {
        movieDao.insertAll(MovieCacheMapper.mapMoviesToCache(list))
    }

    override fun updateMovies(list: List<MovieVO>) {
        movieDao.updateData(MovieCacheMapper.mapMoviesToCache(list))
    }

}