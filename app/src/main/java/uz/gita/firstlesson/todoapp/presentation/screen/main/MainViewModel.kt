package uz.gita.firstlesson.todoapp.presentation.screen.main

import androidx.lifecycle.LiveData

interface MainViewModel {
    val openAddTaskScreenLiveData: LiveData<Unit>
    val openTrashScreenLiveData: LiveData<Unit>
    fun openAddTaskScreen()
    fun openTrashScreen()
}

