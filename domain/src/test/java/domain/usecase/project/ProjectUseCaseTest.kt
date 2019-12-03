package domain.usecase.project

import com.rf.tw_sample.domain.entity.Project
import com.rf.tw_sample.domain.repository.ProjectRepository
import com.rf.tw_sample.domain.usecase.ProjectUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class ProjectUseCaseTest {

    private lateinit var useCase: ProjectUseCase

    @Mock
    lateinit var projectRepository: ProjectRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = ProjectUseCase(projectRepository)
    }

    @Test
    fun getProjectsSuccess() {
        `when`(projectRepository.loadProjects()).thenReturn(
            Single.just(
                listOf(
                    Project(
                        "1",
                        "Project 1",
                        true
                    ),
                    Project(
                        "2",
                        "Project 2",
                        true
                    ),
                    Project(
                        "3",
                        "Project 3",
                        false
                    )
                )
            )
        )

        useCase.loadProjects().test()
            .assertComplete()

        verify(projectRepository, times(1)).loadProjects()
    }
}