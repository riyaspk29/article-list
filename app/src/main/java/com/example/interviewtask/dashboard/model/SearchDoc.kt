package com.example.interviewtask.dashboard.model

import com.google.gson.annotations.SerializedName

data class SearchDoc (
        @SerializedName("docs") val docs: List<Article>
)