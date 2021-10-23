package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieData
import io.reactivex.Single

@Dao
interface MovieDao {
    @Query("SELECT * FROM movies")
    fun getAllMovies(): Single<List<MovieData>>

    @Transaction
    fun updateData(movies: List<MovieData>) {
        deleteAll()
        insertAll(movies)
    }

    @Insert
    fun insertAll(movies: List<MovieData>)

    @Query("DELETE FROM movies")
    fun deleteAll()
}