package com.example.interviewtask.dashboard.model

import java.io.Serializable

data class Article(val id: Long, val title: String, val abstract: String, val multimedia: List<Multimedia>):Serializable