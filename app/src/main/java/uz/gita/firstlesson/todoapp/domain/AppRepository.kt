package uz.gita.firstlesson.todoapp.domain

import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

interface AppRepository {
    fun getAllTodoTasks() : List<TaskUIData>
    fun getAllTodoTasks2() : LiveData<List<TaskEntity>>
    fun getAllDoingTasks() : LiveData<List<TaskEntity>>
    fun getAllDoneTasks() : LiveData<List<TaskEntity>>
    fun getAllTrashTasks() : LiveData<List<TaskEntity>>

    fun addTask(data: TaskUIData)
    fun addTask(id: Int, title: String, description: String)
    fun updateTaskStatus(id: Int, newStatus: Int)

    fun moveToTrash(id: Int, state : Int)
    fun restoreFromTrash(id: Int)
    fun deleteItem(id: Int)
    fun getTaskById(id: Int) : TaskEntity
}