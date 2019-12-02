package com.rf.tw_sample.data.response

import com.google.gson.annotations.SerializedName
import com.rf.tw_sample.domain.entity.Project

data class ProjectsResponse(
    @SerializedName("STATUS")
    val status: String,
    @SerializedName("projects")
    val projects: List<ProjectResponse>
)

data class ProjectResponse(
    @SerializedName("announcement")
    val announcement: String = "",
    @SerializedName("created-on")
    val createdOn: String = "",
    @SerializedName("description")
    val description: String = "",
    @SerializedName("endDate")
    val endDate: String = "",
    @SerializedName("id")
    val id: String,
    @SerializedName("isProjectAdmin")
    val isProjectAdmin: Boolean = false,
    @SerializedName("last-changed-on")
    val lastChangedOn: String = "",
    @SerializedName("logo")
    val logo: String = "",
    @SerializedName("name")
    val name: String = "",
    val starred: Boolean = false,
    @SerializedName("startDate")
    val startDate: String = "",
    @SerializedName("status")
    val status: String = "",
    @SerializedName("subStatus")
    val subStatus: String = ""
)

fun ProjectResponse.mapToEntity() =
    Project(this.id, this.name, this.starred)