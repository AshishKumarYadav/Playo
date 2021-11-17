package com.ashish.playo.api

import com.ashish.playo.model.ResponseModel
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitServices {
//    868b41a610b14a2c96676d1b32569287
    //GET https://newsapi.org/v2/top-headlines?country=us&apiKey=868b41a610b14a2c96676d1b32569287
    @GET("top-headlines?sources=techcrunch&apiKey=868b41a610b14a2c96676d1b32569287")
    suspend fun getAllNews(): Response<ResponseModel>

    companion object {

        var retrofitService: RetrofitServices? = null

        fun getInstance() : RetrofitServices {

            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://newsapi.org/v2/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitServices::class.java)
            }
            return retrofitService!!
        }
    }
}