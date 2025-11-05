package uz.gita.firstlesson.todoapp.pages.done

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.presentation.page.doing.DoingViewModelImpl
import uz.gita.firstlesson.todoapp.presentation.page.done.DoneViewModelImpl

class DoneViewModelImplTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: AppRepository = mock(AppRepository::class.java)
    private val viewModel = DoneViewModelImpl(repository)

    @Test
    fun `changeTaskStatus ishlaganda updateTaskStatus chaqirilishi`() {
        val taskId = 1
        val newStatus = 2
        viewModel.updateStatus(taskId, newStatus)
        verify(repository).updateTaskStatus(taskId, newStatus)
    }

    @Test
    fun `moveToTrash ishlaganda moveToTrash chaqirilishi`() {
        val taskId = 1
        viewModel.moveToTrash(taskId)
        verify(repository).moveToTrash(taskId, 2)
    }
}