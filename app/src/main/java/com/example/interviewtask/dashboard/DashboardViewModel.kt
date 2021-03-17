package com.example.interviewtask.dashboard

import android.os.CountDownTimer
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewtask.BuildConfig
import com.example.interviewtask.dashboard.model.Article
import com.example.interviewtask.network.ArticleApi
import kotlinx.coroutines.*
import java.util.*
import kotlin.collections.ArrayList


class DashboardViewModel:ViewModel() {

    private val _articles = MutableLiveData<List<Article>>()
    val articles: LiveData<List<Article>>
        get() = _articles

    private val _topStories = MutableLiveData<List<Article>>()
    val topStories: LiveData<List<Article>>
        get() = _topStories

    private val _page = MutableLiveData<Int>()
    val page: LiveData<Int>
        get() = _page

    var search = MutableLiveData<String>()

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)


    private val timer :CountDownTimer

    init {
        getTopStories(BuildConfig.API_KEY)
        getArticles(BuildConfig.API_KEY,"")

        _page.value = 0

        timer = object : CountDownTimer(24*60*60*1000, 3000L) {
            override fun onTick(millisUntilFinished: Long) {
                _page.value?.let {
                    if (it >= _topStories.value?.size ?: 0) {
                        _page.value = 0
                    }else{
                        _page.value = _page.value?.plus(1)
                    }
                }
            }

            override fun onFinish() {

            }
        }
    }

    private fun getArticles(key: String, q:String) {
        coroutineScope.launch {
            val responseBody = ArticleApi.retrofitService.getArticles(key,q)
            try {
                _articles.value = responseBody.data.docs
            } catch (e: Exception) {
                _articles.value = ArrayList()
            }
        }
    }

    private fun getTopStories(key: String) {
        coroutineScope.launch {
            val responseBody = ArticleApi.retrofitService.getTopStories(key)
            try {
                _topStories.value = responseBody.articles
                timer.start()
            } catch (e: Exception) {
                _topStories.value = ArrayList()
            }
        }
    }

    fun searchButtonTap(view:View){
        search.value?.let {
            _articles.value = ArrayList()
            getArticles(BuildConfig.API_KEY, it)
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()

        timer.cancel()
    }
}