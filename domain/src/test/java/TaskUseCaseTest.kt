import com.rf.tw_sample.domain.entity.Task
import com.rf.tw_sample.domain.repository.TaskRepository
import com.rf.tw_sample.domain.usecase.TaskUseCase
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations

class TaskUseCaseTest {

    private lateinit var useCase: TaskUseCase

    @Mock
    lateinit var taskRepository: TaskRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        useCase = TaskUseCase(taskRepository)
    }

    @Test
    fun getTasksSuccess() {
        val testId = "testId"
        `when`(taskRepository.loadTasksByProject(testId)).thenReturn(
            Single.just(
                listOf(
                    Task(
                        "Task 1",
                        "it's a task",
                        "John Doe",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    ), Task(
                        "Task 2",
                        "it's a task",
                        "John Doe",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    ), Task(
                        "Task 3",
                        "it's a task",
                        "John Doe",
                        "https://lh3.googleusercontent.com/Xn1X_pIDKMxzvIyqCDT6l0_G8-3IAHkQe-LhD4OTYAF7qBlRUmBcpiBkWQIswyDtLqM=s360-rw"
                    )
                )
            )
        )

        useCase.loadTasksByProject(testId).test()
            .assertComplete()

        verify(taskRepository, times(1)).loadTasksByProject(testId)
    }
}