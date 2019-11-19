package com.rf.tw_sample.data.api;

import com.rf.tw_sample.data.response.ProjectsResponse;
import com.rf.tw_sample.data.response.TasksResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("projects.json")
    Single<ProjectsResponse> getProjects();

    @GET("projects/{id}/tasks.json")
    Single<TasksResponse> getTasksByProject(@Path("id") String projectId);
}
