package com.example.interviewtask.dashboard.slider

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.interviewtask.dashboard.model.Article

class SlidePageFragmentViewModel(page: Int, article: Article): ViewModel() {

    private val _articleTitle = MutableLiveData<String>()
    val articleTitle: LiveData<String>
        get() = _articleTitle

    private val _pageLiveData = MutableLiveData<Int>()
    val pageLiveData: LiveData<Int>
        get() = _pageLiveData

    private val _articleLiveData = MutableLiveData<Article>()
    val articleLiveData: LiveData<Article>
        get() = _articleLiveData

    init {
        _articleTitle.value = article.abstract
        _pageLiveData.value = page
        _articleLiveData.value = article
    }
}