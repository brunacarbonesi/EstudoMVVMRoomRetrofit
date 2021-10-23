package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieData

@Database(entities = [MovieData::class], version = 1, exportSchema = false)
public abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}