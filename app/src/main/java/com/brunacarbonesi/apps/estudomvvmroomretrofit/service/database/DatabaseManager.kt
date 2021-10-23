package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import android.content.Context
import androidx.room.Room

object DatabaseManager {
    var movieDao: MovieDao? = null

    fun initialize(context: Context): MovieDao {
        movieDao = createDatabase(context).movieDao()
        return movieDao as MovieDao
    }

    fun createDatabase(context: Context): AppDatabase {
        val databaseUser = this.let {
            Room.databaseBuilder(context, AppDatabase::class.java, "movies")
                .allowMainThreadQueries()
                .build()
        }

        return databaseUser
    }
}