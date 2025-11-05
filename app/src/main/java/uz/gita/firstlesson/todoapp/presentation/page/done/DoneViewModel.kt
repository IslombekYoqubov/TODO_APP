package uz.gita.firstlesson.todoapp.presentation.page.done

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface DoneViewModel {
    // Contract view
    val emptyStateLiveData: LiveData<Boolean>
    val tasksLiveData: LiveData<List<TaskUIData>>
    val taskEntityLiveData : LiveData<List<TaskEntity>>

    // Contract -> Presenter
    fun load()
    fun updateStatus(id : Int, state : Int)
    fun moveToTrash(id: Int)
}


