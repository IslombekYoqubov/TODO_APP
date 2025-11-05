package uz.gita.firstlesson.todoapp.presentation.screen.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModelImpl : ViewModel(), MainViewModel {
    override val openAddTaskScreenLiveData = MutableLiveData<Unit>()
    override val openTrashScreenLiveData = MutableLiveData<Unit>()

    override fun openAddTaskScreen() {
        openAddTaskScreenLiveData.value = Unit
    }

    override fun openTrashScreen() {
        openTrashScreenLiveData.value = Unit
    }
}


