package com.rf.tw_sample.data.response

import com.google.gson.annotations.SerializedName
import com.rf.tw_sample.domain.entity.Priority
import com.rf.tw_sample.domain.entity.Tag
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
    @SerializedName("responsible-party-names")
    val responsiblePartyName: String = "",
    @SerializedName("tasklist-private")
    val isPrivate: Boolean = false,
    @SerializedName("todo-list-name")
    val name: String = "",
    @SerializedName("priority")
    val priority: String = "",
    @SerializedName("tags")
    val tags: List<TagResponse> = listOf()

)

data class TagResponse(
    val id: Int,
    val name: String,
    val color: String
)

fun TagResponse.mapToEntity() =
    Tag(
        id = this.id,
        name = this.name,
        color = this.color
    )

fun TaskResponse.mapToEntity() =
    Task(
        name = this.content,
        description = this.description,
        responsibleParty = this.responsiblePartyName,
        createdOn = createdOn,
        priority = when (this.priority.toLowerCase()) {
            "low" -> Priority.Low
            "medium" -> Priority.Medium
            "high" -> Priority.High
            else -> Priority.None
        },
        tags = tags.map {
            it.mapToEntity()
        }
    )