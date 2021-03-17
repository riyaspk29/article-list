package com.example.interviewtask.login

import android.R
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bumptech.glide.Glide
import com.example.interviewtask.BuildConfig
import com.example.interviewtask.network.ArticleApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class LoginViewModel:ViewModel() {

    private val _navigateToDashBoard = MutableLiveData<Boolean>()
    val navigateToDashBoard: LiveData<Boolean>
        get() = _navigateToDashBoard

    private val _loadingApi = MutableLiveData<Boolean>()
    val loadingApi: LiveData<Boolean>
        get() = _loadingApi

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        _loadingApi.value = false
    }

    fun navigationFinished() {
        _navigateToDashBoard.value = false
    }

    fun login(){
        _loadingApi.value = true
        dummyLoginAPI(BuildConfig.API_KEY)
    }

    private fun dummyLoginAPI(key: String) {
        coroutineScope.launch {
            val responseBody = ArticleApi.retrofitService.getTopStories(key)
            try {
                _navigateToDashBoard.value = true
                _loadingApi.value = false
            } catch (e: Exception) {
                _navigateToDashBoard.value = false
                _loadingApi.value = false
            }
        }
    }
}