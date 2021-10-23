package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api

import androidx.room.ColumnInfo
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO

object MovieRemoteMapper {
    fun mapRemote(moviesList: List<MovieVO>) = moviesList.map { map(it) }

    private fun map(movieVO: MovieVO) = MovieVO(
        id = movieVO.id,
        title = movieVO.title,
        overview = movieVO.overview,
        posterPath = movieVO.posterPath,
        backdropPath = movieVO.backdropPath,
        rating = movieVO.rating,
        releaseDate = movieVO.releaseDate
    )
}