package uz.gita.firstlesson.todoapp.presentation.page.todo

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface TodoViewModel {
    // Contract view
    val emptyStateLiveData: LiveData<Boolean>
    val tasksLiveData: LiveData<List<TaskUIData>>
    val tasksEntityLiveData: LiveData<List<TaskEntity>>

    // Contract -> Presenter
    fun load()
    fun changeTaskStatus(id: Int, newStatus: Int)
    fun moveToTrash(id: Int)
}


