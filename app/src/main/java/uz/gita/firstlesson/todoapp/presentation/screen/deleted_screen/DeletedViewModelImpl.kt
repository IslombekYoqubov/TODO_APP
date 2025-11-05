package uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl

class DeletedViewModelImpl(private val repository : AppRepository) : ViewModel(),DeletedViewModel {
    override val liveDataEntity: LiveData<List<TaskEntity>> = repository.getAllTrashTasks()
    override fun deleteItem(id: Int) {
        repository.deleteItem(id)
    }

    override fun restoreItem(id: Int) {
        repository.restoreFromTrash(id)
    }
}