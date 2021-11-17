package com.ashish.playo.model

import com.google.gson.annotations.SerializedName

data class ResponseModel(@SerializedName("status")
                         val status: String = "",

                         @SerializedName("totalResults")
                         val totalResults: Int = 0,

                         @SerializedName("articles")
                         val articles: List<NewsArticle> = emptyList())


