package com.rf.tw_sample.domain.usecase;

import com.rf.tw_sample.domain.entity.Project;
import com.rf.tw_sample.domain.repository.ProjectRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;

public class ProjectUseCase {

    private ProjectRepository projectRepository;

    @Inject
    public ProjectUseCase(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public Single<List<Project>> loadProjects() {
        return projectRepository.loadProjects();
    }
}
