package com.example.kenyanstories.network

import com.example.newsapp.domain.categories.Cateogory
import com.example.newsapp.domain.news.News
import retrofit2.Call
import retrofit2.http.GET


const val API_KEY = "e2b15072fb34449283fb056ff442f670"

interface Api {
    //Argentina
    @GET("/v2/top-headlines?country=ar&apiKey=$API_KEY")
    fun getNewsArgentina(): Call<News>

    //usa
    @GET("/v2/top-headlines?country=us&apiKey=$API_KEY")
    fun getNewsUsa(): Call<News>

    //india
    @GET("/v2/top-headlines?country=in&apiKey=$API_KEY")
    fun getNewsIndia(): Call<News>

    //france
    @GET("/v2/top-headlines?country=fr&apiKey=$API_KEY")
    fun getNewsFrance(): Call<News>

    //serbia
    @GET("/v2/top-headlines?country=rs&apiKey=$API_KEY")
    fun getNewsSerbia(): Call<News>

    //brazil
    @GET("/v2/top-headlines?country=br&apiKey=$API_KEY")
    fun getNewsBrazil(): Call<News>

    //colombia
    @GET("/v2/top-headlines?country=co&apiKey=$API_KEY")
    fun getNewsColombia(): Call<News>

    //categories
    @GET("/v2/sources?apiKey=$API_KEY")
    fun getCategories(): Call<Cateogory>
}