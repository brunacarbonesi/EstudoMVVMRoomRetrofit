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

    @GET("movie/top_rated")
    fun getTopRatedMovies(
        @Query("api_key") apiKey: String = "28462c086aa50bfdb7f5d824170f5df7",
        @Query("page") page: Int
    ): Call<MoviesResponse>
}