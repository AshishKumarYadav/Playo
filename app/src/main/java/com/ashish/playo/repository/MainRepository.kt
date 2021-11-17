package com.ashish.playo.repository

import androidx.lifecycle.LiveData
import com.ashish.playo.api.RetrofitServices
import com.ashish.playo.api.RetrofitServices.Companion.retrofitService

class MainRepository constructor(val retrofitServices: RetrofitServices) {

   suspend fun getAllNewsItem() = retrofitService?.getAllNews()

}