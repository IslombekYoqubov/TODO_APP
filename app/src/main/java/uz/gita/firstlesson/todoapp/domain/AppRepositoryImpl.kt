package uz.gita.firstlesson.todoapp.domain

import android.util.Log
import androidx.lifecycle.LiveData
import uz.gita.firstlesson.todoapp.data.mapper.toEntity
import uz.gita.firstlesson.todoapp.data.mapper.toUiData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.AppDatabase
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

class AppRepositoryImpl private constructor() : AppRepository {
    companion object {
        private lateinit var instance: AppRepository

        fun init() {
            if (!(Companion::instance.isInitialized))
                instance = AppRepositoryImpl()
        }

        fun getInstance() = instance
    }
    private val taskDao = AppDatabase.getInstance().getTaskDao()

    override fun getAllTodoTasks(): List<TaskUIData> {
        return taskDao.getAllTodoTasks().map { it.toUiData() }
    }

    override fun getAllTodoTasks2(): LiveData<List<TaskEntity>> = taskDao.getAllTodoTasks2()

    override fun getAllDoingTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getAllDoingTasks()
    }

    override fun getAllDoneTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getAllDoneTasks()
    }

    override fun getAllTrashTasks(): LiveData<List<TaskEntity>> {
        return taskDao.getAllTrashTasks()
    }

    override fun addTask(data: TaskUIData) {
        taskDao.insert(data.toEntity())
    }

    override fun addTask(id: Int, title: String, description: String) {
        val tt = taskDao.getTaskById1(id)
        var state = 0
        if(tt != null){
            state = tt.state
        }
        taskDao.insert(TaskEntity(id, title, description, state))
    }

    override fun updateTaskStatus(id: Int, newStatus: Int) {
        taskDao.updateTaskStatus(id, newStatus)
    }

    override fun moveToTrash(id: Int, state : Int) {
        val oldState = taskDao.getTaskById(id)
        Log.d("TEST", "oldState = $oldState")
        taskDao.moveToTrash(id, oldState.state)
    }

    override fun restoreFromTrash(id: Int) {
        taskDao.restoreFromTrash(id)
    }

    override fun deleteItem(id: Int) {
        taskDao.deleteItem(id)
    }

    override fun getTaskById(id: Int) : TaskEntity {
        return taskDao.getTaskById(id)
    }
}


