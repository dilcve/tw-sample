package com.rf.tw_sample.domain.repository;

import com.rf.tw_sample.domain.entity.Task;

import java.util.List;

import io.reactivex.Single;

public interface TaskRepository {

    Single<List<Task>> loadTasksByProject(String id);
}
