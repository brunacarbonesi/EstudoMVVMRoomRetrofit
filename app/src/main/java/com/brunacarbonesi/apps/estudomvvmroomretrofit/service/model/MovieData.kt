package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "movies")
data class MovieData(
    @PrimaryKey
    @ColumnInfo(name = "id") var id: Long,
    @ColumnInfo(name = "title") var title: String,
    @ColumnInfo(name = "overview") var overview: String,
    @ColumnInfo(name = "poster_path") var posterPath: String,
    @ColumnInfo(name = "backdrop_path") var backdropPath: String,
    @ColumnInfo(name = "vote_average") var rating: Float,
    @ColumnInfo(name = "release_date") var releaseDate: String

)
