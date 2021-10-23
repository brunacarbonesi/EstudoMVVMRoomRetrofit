package com.brunacarbonesi.apps.estudomvvmroomretrofit

import android.app.Application
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database.DatabaseManager

open class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        DatabaseManager.initialize(this)
    }
}