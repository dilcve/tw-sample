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
                        "Amazing project",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    ),
                    Project(
                        "2",
                        "Project 2",
                        "Amazing project 2",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    ),
                    Project(
                        "3",
                        "Project 3",
                        "Amazing project 3",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    )
                )
            )
        )

        useCase.loadProjects().test()
            .assertComplete()

        verify(projectRepository, times(1)).loadProjects()
    }
}