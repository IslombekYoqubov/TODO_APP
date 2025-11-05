package uz.gita.firstlesson.todoapp.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen.DeletedViewModelImpl

class DeletedScreenTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: AppRepository = Mockito.mock(AppRepository::class.java)
    private val viewModel = DeletedViewModelImpl(repository)

    @Test
    fun `delete item function ishlatilishi`(){
        viewModel.deleteItem(3)
        verify(repository).deleteItem(3)
    }

    @Test
    fun `restore item function ishlatilishi`(){
        viewModel.restoreItem(3)
        verify(repository).restoreFromTrash(3)
    }
}