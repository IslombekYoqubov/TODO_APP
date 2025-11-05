package uz.gita.firstlesson.todoapp.presentation.page.done

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl

class DoneViewModelImpl constructor(private val repository : AppRepository) : ViewModel(), DoneViewModel {
    override val emptyStateLiveData = MutableLiveData<Boolean>()
    override val tasksLiveData = MutableLiveData<List<TaskUIData>>()
    override val taskEntityLiveData: LiveData<List<TaskEntity>> = repository.getAllDoneTasks()

    init {
        load()
    }

    override fun load() {}

    override fun updateStatus(id: Int, state: Int) {
        repository.updateTaskStatus(id, state)
    }

    override fun moveToTrash(id: Int) {
        repository.moveToTrash(id, 2)
    }
}


