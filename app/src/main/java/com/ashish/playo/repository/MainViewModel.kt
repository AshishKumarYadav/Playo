package com.ashish.playo.repository

import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ashish.playo.model.NewsArticle
import com.ashish.playo.model.ResponseModel
import kotlinx.coroutines.*

class MainViewModel constructor(private val mainRepository: MainRepository): ViewModel() {

    val newsList = MutableLiveData<ResponseModel>()
    var job: Job? = null

    fun getAllNews() {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllNewsItem()
            withContext(Dispatchers.Main) {
                if (response!!.isSuccessful) {

                    newsList.postValue(response.body())


                }
            }
        }

    }




}