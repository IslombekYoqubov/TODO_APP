package uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface DeletedViewModel {
    val liveDataEntity : LiveData<List<TaskEntity>>

    fun deleteItem(id : Int)
    fun restoreItem(id: Int)
}