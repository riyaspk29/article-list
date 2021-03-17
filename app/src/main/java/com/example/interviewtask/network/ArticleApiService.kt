package com.example.interviewtask.network

import com.example.interviewtask.dashboard.model.ArticleResponse
import com.example.interviewtask.dashboard.model.SearchResponse
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.nytimes.com/svc/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ArticleApiService {

    @GET("search/v2/articlesearch.json")
    suspend fun getArticles(@Query("api-key") key: String, @Query("q") q: String): SearchResponse

    @GET("topstories/v2/arts.json")
    suspend fun getTopStories(@Query("api-key") key: String): ArticleResponse
}


object ArticleApi {
    val retrofitService : ArticleApiService by lazy { retrofit.create(ArticleApiService::class.java) }
}

