package uz.gita.firstlesson.todoapp.presentation.screen.add

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl

class AddTaskViewModelImpl(private val repository : AppRepository) : ViewModel(), AddTaskViewModel {
    override val buttonStateLiveData = MutableLiveData<Boolean>()
    override val successLiveData = MutableLiveData<String>()
    override val finishScreenLiveData = MutableLiveData<Unit>()
    private var title = ""
    private var description = ""

    override fun setTitle(title: String) {
        this.title = title
        buttonStateLiveData.value = this.title.length > 5 && this.description.length > 10
    }

    override fun setDescription(description: String) {
        this.description = description
        buttonStateLiveData.value = this.title.length > 5 && this.description.length > 10
    }

    override fun insert(id : Int, title: String, description: String) {
        repository.addTask(id, title, description)
        successLiveData.value = "Success"
        finishScreenLiveData.value = Unit
    }

    override fun getItemById(id: Int) : TaskEntity {
        return repository.getTaskById(id)
    }
}


