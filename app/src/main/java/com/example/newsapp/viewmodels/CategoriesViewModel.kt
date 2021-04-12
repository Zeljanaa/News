package com.example.newsapp.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.kenyanstories.network.Network
import com.example.newsapp.adapters.CategoryAdapter
import com.example.newsapp.domain.categories.Cateogory
import com.example.newsapp.domain.categories.Source
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import retrofit2.await
import timber.log.Timber
import java.io.IOException

class CategoriesViewModel(application: Application) : AndroidViewModel(application) {
    /**
     *  LiveData gives us updated data when they change.
     * */
    private var allCategory: MutableLiveData<Cateogory>


    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var adapter: CategoryAdapter

    init {
        Timber.d("News ViewModel created")
        allCategory = MutableLiveData()


        adapter = CategoryAdapter()
        fetchCategoriesInCoroutine()
    }

    fun getMyAdapter(): CategoryAdapter {
        return adapter
    }

    fun setAdapterData(data: List<Source>) {
        adapter.apply {
            setDataList(data)
            notifyDataSetChanged()
        }
    }


    fun getAllCategories(): MutableLiveData<Cateogory> {
        return allCategory
    }

    private fun fetchCategoriesInCoroutine() = viewModelScope.launch {
        try {
            val fetchingCategory = Network.apiServiceNews.getCategories().await()
            allCategory.postValue(fetchingCategory)
        } catch (networkError: IOException) {
            allCategory.postValue(null)
        }
    }
}