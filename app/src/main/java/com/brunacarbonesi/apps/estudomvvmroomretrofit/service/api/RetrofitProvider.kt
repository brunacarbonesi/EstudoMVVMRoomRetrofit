package com.brunacarbonesi.apps.estudomvvmroomretrofit.service.api

import com.brunacarbonesi.apps.estudomvvmroomretrofit.utils.AppConstants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitProvider {
    companion object {
        private val gson: Gson by lazy { GsonBuilder().create() }

        private val okHttp: OkHttpClient by lazy {
            OkHttpClient.Builder()
                .build()
        }

        val retrofit: Retrofit by lazy {
            Retrofit.Builder()
                .baseUrl(AppConstants.URL)
                .client(okHttp)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        }
    }
}