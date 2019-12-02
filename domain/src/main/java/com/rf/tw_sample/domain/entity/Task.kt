package com.rf.tw_sample.domain.entity

//Using just the UI fields
data class Task(
    val name: String,
    val description: String,
    val createdOn: String,
    val responsibleParty: String,
    val priority: Priority,
    val tags: List<Tag>
)

data class Tag(val id: Int, val name: String, val color: String)

sealed class Priority{
    object Low: Priority()
    object Medium: Priority()
    object High: Priority()
    object None: Priority()
}