package uz.gita.firstlesson.todoapp.presentation.page.doing

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface DoingViewModel {
    // Contract view
    val emptyStateLiveData: LiveData<Boolean>
    val tasksLiveData: LiveData<List<TaskUIData>>
    val taskEntityLiveData : LiveData<List<TaskEntity>>

    // Contract -> Presenter
    fun load()
    fun changeTaskStatus(id : Int, state : Int)
    fun moveToTrash(id: Int)
}

