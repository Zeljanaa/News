package com.example.kenyanstories.network

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.moshi.MoshiConverterFactory


object Network {
    private const val BASE_URL: String = "https://newsapi.org"

    private val retrofitBuilderNews: Retrofit.Builder by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
    }


    val apiServiceNews: Api by lazy {
        retrofitBuilderNews
            .build()
            .create(Api::class.java)
    }

}