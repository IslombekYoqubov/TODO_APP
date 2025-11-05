package uz.gita.firstlesson.todoapp.presentation.screen.add

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface AddTaskViewModel {
    val buttonStateLiveData: LiveData<Boolean>
    val successLiveData: LiveData<String>
    val finishScreenLiveData: LiveData<Unit>

    fun setTitle(title: String)
    fun setDescription(description: String)
    fun insert(id : Int, title: String, description: String)
    fun getItemById(id: Int) : TaskEntity
}

