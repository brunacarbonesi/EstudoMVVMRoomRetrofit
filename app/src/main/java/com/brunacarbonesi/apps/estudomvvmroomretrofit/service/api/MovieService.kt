package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api

import com.brunacarbonesi.apps.estudomvvmroomretrofit.service.model.MoviesResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {

    @GET("movie/popular")
    fun getPopularMovies(
        @Query("api_key") apiKey: String,
        @Query("page") page: Int
    ): Single<MoviesResponse>

}