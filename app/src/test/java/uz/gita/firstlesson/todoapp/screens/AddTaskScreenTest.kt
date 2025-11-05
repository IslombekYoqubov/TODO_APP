package uz.gita.firstlesson.todoapp.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.atLeastOnce
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.presentation.screen.add.AddTaskViewModelImpl

class AddTaskScreenTest {
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private val repository: AppRepository = Mockito.mock(AppRepository::class.java)
    private val viewModel = AddTaskViewModelImpl(repository)

    @Test
    fun `togri setTitle qilish`(){
        val observer = mock(Observer::class.java) as Observer<Boolean>
        viewModel.buttonStateLiveData.observeForever(observer)
        viewModel.setTitle("123456")
        viewModel.setDescription("12345678901")
        verify(observer, atLeastOnce()).onChanged(true)
    }

    @Test
    fun `xato setTitle qilish`(){
        val observer = mock(Observer::class.java) as Observer<Boolean>
        viewModel.buttonStateLiveData.observeForever(observer)
        viewModel.setTitle("12346")
        viewModel.setDescription("12345678901")
        verify(observer, atLeastOnce()).onChanged(false)
    }

    @Test
    fun insert(){
        val successLiveDataObserver = mock(Observer::class.java) as Observer<String>
        val finishScreenLiveDataObserver = mock(Observer::class.java) as Observer<Unit>
        viewModel.successLiveData.observeForever(successLiveDataObserver)
        viewModel.finishScreenLiveData.observeForever(finishScreenLiveDataObserver)
        viewModel.insert(1, "12122", "assdsdddsdfsdsd")
        verify(successLiveDataObserver).onChanged("Success")
        verify(finishScreenLiveDataObserver).onChanged(Unit)
    }

    @Test
    fun getItemById(){
        val task = TaskEntity(1, "121212", "dssdsdasdcv")
        `when`(repository.getTaskById(1)).thenReturn(task)
    }
}