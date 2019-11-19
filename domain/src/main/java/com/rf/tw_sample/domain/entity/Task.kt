package com.rf.tw_sample.domain.entity

//Using just the UI fields
data class Task(
    val name: String,
    val description: String,
    val author: String,
    val creatorAvatarUrl: String
)