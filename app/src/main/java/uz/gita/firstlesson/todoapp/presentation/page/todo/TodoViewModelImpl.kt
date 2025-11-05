package uz.gita.firstlesson.todoapp.presentation.page.todo

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.domain.AppRepository

class TodoViewModelImpl(
    private val repository: AppRepository
) : ViewModel(), TodoViewModel {

    override val emptyStateLiveData = MutableLiveData<Boolean>()
    override val tasksLiveData = MutableLiveData<List<TaskUIData>>()
    override val tasksEntityLiveData = repository.getAllTodoTasks2()

    override fun load() {}

    override fun changeTaskStatus(id: Int, newStatus: Int) {
        repository.updateTaskStatus(id, newStatus)
    }

    override fun moveToTrash(id: Int) {
        repository.moveToTrash(id, 0)
    }
}