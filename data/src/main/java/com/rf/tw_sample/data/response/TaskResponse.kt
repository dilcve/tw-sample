package com.rf.tw_sample.data.response

import com.google.gson.annotations.SerializedName
import com.rf.tw_sample.domain.entity.Task

data class TasksResponse(
    @SerializedName("STATUS")
    val status: String = "",
    @SerializedName("todo-items")
    val tasks: List<TaskResponse> = listOf()
)

data class TaskResponse(
    val completed: Boolean = false,
    val content: String = "",
    @SerializedName("created-on")
    val createdOn: String = "",
    @SerializedName("creator-avatar-url")
    val creatorAvatarUrl: String = "",
    @SerializedName("creator-firstname")
    val creatorFirstName: String = "",
    @SerializedName("creator-lastname")
    val creatorLastName: String = "",
    val description: String = "",
    @SerializedName("due-date")
    val dueDate: String = "",
    @SerializedName("responsible-party-firstname")
    val responsiblePartyFirstname: String = "",
    @SerializedName("tasklist-private")
    val isPrivate: Boolean = false,
    @SerializedName("todo-list-name")
    val name: String = ""
)

fun TaskResponse.mapToEntity() =
    Task(
        this.name,
        this.description,
        "${this.creatorFirstName} ${this.creatorFirstName}",
        this.creatorAvatarUrl
    )