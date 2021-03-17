package com.example.interviewtask.dashboard.model

import com.google.gson.annotations.SerializedName

data class SearchResponse(
    val status:String,
    @SerializedName("response") val data: SearchDoc
)