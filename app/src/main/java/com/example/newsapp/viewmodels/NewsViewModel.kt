package com.example.newsapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kenyanstories.network.Network
import com.example.newsapp.adapters.NewsAdapter
import com.example.newsapp.domain.news.Article
import com.example.newsapp.domain.news.News
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.await
import timber.log.Timber
import java.io.IOException

class NewsViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allNews: MutableLiveData<News>
    private var allNewsArgentina: MutableLiveData<News>
    private var allNewsIndia: MutableLiveData<News>
    private var allNewsFrance: MutableLiveData<News>
    private var allNewsSerbia: MutableLiveData<News>
    private var allNewsColombia: MutableLiveData<News>
    private var allNewsBrazil: MutableLiveData<News>

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var adapter: NewsAdapter

    init {
        Timber.d("News ViewModel created")
        allNews = MutableLiveData()
        allNewsArgentina = MutableLiveData()
        allNewsIndia = MutableLiveData()
        allNewsFrance = MutableLiveData()
        allNewsSerbia = MutableLiveData()
        allNewsColombia = MutableLiveData()
        allNewsBrazil = MutableLiveData()

        adapter = NewsAdapter()
        fetchNewsAlertInCoroutine()
        fetchNewsAlertInCoroutineArgentina()
        fetchNewsAlertInCoroutineIndia()
        fetchNewsAlertInCoroutineFrance()
        fetchNewsAlertInCoroutineSerbia()
        fetchNewsAlertInCoroutineBrazil()
        fetchNewsAlertInCoroutineColombia()
    }

    fun getMyAdapter(): NewsAdapter {
        return adapter
    }

    fun setAdapterData(data: List<Article>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }


    fun getAllNewsUsa(): MutableLiveData<News> {
        return allNews
    }

    private fun fetchNewsAlertInCoroutine() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsUsa().await()
            allNews.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNews.postValue(null)
        }
    }


    /**
     * *********************************************************************************************
     * */

    fun getAllNewsArgentina(): MutableLiveData<News> {
        return allNewsArgentina
    }

    private fun fetchNewsAlertInCoroutineArgentina() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsArgentina().await()
            allNewsArgentina.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsArgentina.postValue(null)
        }
    }

    /**
     * *********************************************************************************************
     * */

    fun getAllNewsIndia(): MutableLiveData<News> {
        return allNewsIndia
    }

    private fun fetchNewsAlertInCoroutineIndia() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsIndia().await()
            allNewsIndia.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsIndia.postValue(null)
        }
    }

    /**
     * *********************************************************************************************
     * */

    fun getAllNewsFrance(): MutableLiveData<News> {
        return allNewsFrance
    }

    private fun fetchNewsAlertInCoroutineFrance() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsFrance().await()
            allNewsFrance.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsFrance.postValue(null)
        }
    }

    /**
     * *********************************************************************************************
     * */


    fun getAllNewsSerbia(): MutableLiveData<News> {
        return allNewsSerbia
    }

    private fun fetchNewsAlertInCoroutineSerbia() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsSerbia().await()
            allNewsSerbia.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsSerbia.postValue(null)
        }
    }

    /**
     * *********************************************************************************************
     * */


    fun getAllNewsBrazil(): MutableLiveData<News> {
        return allNewsBrazil
    }

    private fun fetchNewsAlertInCoroutineBrazil() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsBrazil().await()
            allNewsBrazil.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsBrazil.postValue(null)
        }
    }

    /**
     * *********************************************************************************************
     * */


    fun getAllNewsColombia(): MutableLiveData<News> {
        return allNewsColombia
    }

    private fun fetchNewsAlertInCoroutineColombia() = viewModelScope.launch {
        try {
            val fetchingNews = Network.apiServiceNews.getNewsColombia().await()
            allNewsColombia.postValue(fetchingNews)
        } catch (networkError: IOException) {
            allNewsColombia.postValue(null)
        }
    }
}