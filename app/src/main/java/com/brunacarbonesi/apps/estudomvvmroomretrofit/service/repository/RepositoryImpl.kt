package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.repository

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api.MovieService
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api.RemoteDataSourceImpl
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api.RetrofitProvider
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database.DatabaseManager
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.database.MoviesCacheSourceImpl
import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MovieVO
import io.reactivex.Single

class RepositoryImpl: Repository {

    val service: MovieService by lazy {
        RetrofitProvider.retrofit.create(MovieService::class.java)
    }

    fun remoteDataSource() = RemoteDataSourceImpl(service)

    val remoteDataSource = remoteDataSource()
    private lateinit var cacheDataSource: MoviesCacheSourceImpl

    override fun getMovies(
        verifyAvailableNetwork: Boolean
    ): Single<List<MovieVO>> {

        val userDao = DatabaseManager.movieDao ?: return Single.just(emptyList())
        cacheDataSource = MoviesCacheSourceImpl(userDao)

        return if (verifyAvailableNetwork) {
            getUsersRemote(remoteDataSource, cacheDataSource)
        } else {
            cacheDataSource.getMovies()
                .flatMap { listUsers ->
                    when {
                        listUsers.isEmpty() -> Single.just(emptyList())
                        else -> Single.just(listUsers)
                    }
                }
        }
    }

    private fun getUsersRemote(
        remoteDataSource: RemoteDataSourceImpl,
        cacheDataSource: MoviesCacheSourceImpl?
    ): Single<List<MovieVO>> {
        return remoteDataSource.getMovies()
            .flatMap { listUsers ->
                if (!listUsers.isNullOrEmpty()) {
                    cacheDataSource?.updateMovies(listUsers)
                } else {
                    cacheDataSource?.insertMovies(listUsers)
                }
                Single.just(listUsers)
            }
    }
}