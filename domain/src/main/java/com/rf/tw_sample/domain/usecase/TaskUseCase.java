package com.rf.tw_sample.domain.usecase;

import com.rf.tw_sample.domain.entity.Task;
import com.rf.tw_sample.domain.repository.TaskRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class TaskUseCase {

    private TaskRepository taskRepository;

    @Inject
    public TaskUseCase(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Single<List<Task>> loadTasksByProject(String id) {

        return taskRepository.loadTasksByProject(id);
    }
}
