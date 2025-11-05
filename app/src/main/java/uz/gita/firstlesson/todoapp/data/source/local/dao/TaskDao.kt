package uz.gita.firstlesson.todoapp.data.source.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

@Dao
interface TaskDao {

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.state = 0")
    fun getAllTodoTasks() : List<TaskEntity>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.state = 0")
    fun getAllTodoTasks2() : LiveData<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.state = 1")
    fun getAllDoingTasks() : LiveData<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.state = 2")
    fun getAllDoneTasks() : LiveData<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE TaskEntity.state = -1")
    fun getAllTrashTasks() : LiveData<List<TaskEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(data: TaskEntity)

    @Query("UPDATE TaskEntity SET state = :newState WHERE id = :id")
    fun updateTaskStatus(id: Int, newState: Int)

    @Query("UPDATE TaskEntity SET previousState = :oldState, state = -1 WHERE id = :id")
    fun moveToTrash(id: Int, oldState : Int)

    @Query("""
        UPDATE TaskEntity 
        SET state = COALESCE(previousState, 0), 
            previousState = NULL WHERE id = :id
    """)
    fun restoreFromTrash(id: Int)

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getTaskById(id: Int): TaskEntity

    @Query("SELECT * FROM TaskEntity WHERE id = :id")
    fun getTaskById1(id: Int): TaskEntity?

    @Query("DELETE FROM TaskEntity WHERE id = :id")
    fun deleteItem(id: Int)
}

