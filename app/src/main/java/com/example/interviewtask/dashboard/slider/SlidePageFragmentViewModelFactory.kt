package com.example.interviewtask.dashboard.slider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.interviewtask.dashboard.model.Article

class SlidePageFragmentViewModelFactory(
        private val page: Int,
        private val article: Article) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SlidePageFragmentViewModel::class.java)) {
            return SlidePageFragmentViewModel(page, article) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
