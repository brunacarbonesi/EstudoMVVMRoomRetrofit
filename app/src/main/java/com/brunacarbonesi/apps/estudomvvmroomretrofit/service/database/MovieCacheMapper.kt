package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieData
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

object MovieCacheMapper {

    fun mapCacheToMovies(cacheData: List<MovieData>) = cacheData.map { map(it) }

    private fun map(cacheData: MovieData) = MovieVO (
        id = cacheData.id,
        title = cacheData.title,
        overview = cacheData.overview,
        posterPath = cacheData.posterPath,
        backdropPath = cacheData.backdropPath,
        rating = cacheData.rating,
        releaseDate = cacheData.releaseDate
    )

    fun mapMoviesToCache(movies: List<MovieVO>) = movies.map { map(it) }

    private fun map(data: MovieVO) = MovieData(
        id = data.id,
        title = data.title,
        overview = data.overview,
        posterPath = data.posterPath,
        backdropPath = data.backdropPath,
        rating = data.rating,
        releaseDate = data.releaseDate
    )

}