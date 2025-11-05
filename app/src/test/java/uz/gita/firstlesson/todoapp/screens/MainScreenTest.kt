package uz.gita.firstlesson.todoapp.screens

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import uz.gita.firstlesson.todoapp.presentation.screen.main.MainViewModelImpl

class MainScreenTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModelImpl

    @Before
    fun setUp() {
        viewModel = MainViewModelImpl()
    }

    @Test
    fun `openAddTaskScreen triggers live data`() {
        val observer = mock(Observer::class.java) as Observer<Unit>
        viewModel.openAddTaskScreenLiveData.observeForever(observer)
        viewModel.openAddTaskScreen()
        verify(observer).onChanged(Unit)
    }

    @Test
    fun `openTrashScreen triggers live data`() {
        val observer = mock(Observer::class.java) as Observer<Unit>
        viewModel.openTrashScreenLiveData.observeForever(observer)
        viewModel.openTrashScreen()
        verify(observer).onChanged(Unit)
    }
}