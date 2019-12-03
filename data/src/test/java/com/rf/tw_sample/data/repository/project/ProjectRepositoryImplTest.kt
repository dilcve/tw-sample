package com.rf.tw_sample.data.repository.project

import com.rf.tw_sample.data.api.ApiService
import com.rf.tw_sample.data.repository.ProjectRepositoryImpl
import com.rf.tw_sample.data.response.ProjectResponse
import com.rf.tw_sample.data.response.ProjectsResponse
import com.rf.tw_sample.domain.repository.ProjectRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

class ProjectRepositoryImplTest {

    private lateinit var repository: ProjectRepository

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        repository = ProjectRepositoryImpl(apiService)
    }

    @Test
    fun getProjects() {
        `when`(apiService.projects).thenReturn(
            Single.just<ProjectsResponse>(
                ProjectsResponse(
                    "ok",
                    listOf(ProjectResponse(id = "testId"))
                )
            )
        )

        repository.loadProjects()
            .test()
            .assertValueCount(1)
            .assertComplete()

    }
}