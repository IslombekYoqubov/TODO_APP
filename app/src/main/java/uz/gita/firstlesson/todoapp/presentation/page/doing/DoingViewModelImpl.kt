package uz.gita.firstlesson.todoapp.presentation.page.doing

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl

class DoingViewModelImpl constructor(private val repository : AppRepository) : ViewModel(), DoingViewModel {
    override val emptyStateLiveData = MutableLiveData<Boolean>()
    override val tasksLiveData = MutableLiveData<List<TaskUIData>>()
    override val taskEntityLiveData: LiveData<List<TaskEntity>> = repository.getAllDoingTasks()

    init {
        load()
    }

    override fun load() {}

    override fun changeTaskStatus(id: Int, state: Int) {
        repository.updateTaskStatus(id, state)
        load()
    }

    override fun moveToTrash(id: Int) {
        repository.moveToTrash(id, 0)
    }

    private fun test() {}
}

