package com.example.interviewtask.dashboard.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    val status:String,
    @SerializedName("results") val articles: List<Article>
)