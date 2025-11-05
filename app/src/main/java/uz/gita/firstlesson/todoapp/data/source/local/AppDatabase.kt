package uz.gita.firstlesson.todoapp.data.source.local

import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import uz.gita.firstlesson.todoapp.app.MyApp
import uz.gita.firstlesson.todoapp.data.source.local.dao.TaskDao
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

@Database(entities = [TaskEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTaskDao() : TaskDao

    companion object {
        private lateinit var instance: AppDatabase

        fun getInstance() : AppDatabase {
            if (!(Companion::instance.isInitialized)) {
                instance = Room.databaseBuilder(MyApp.appContext, AppDatabase::class.java, "TaskManager.db")
                    .allowMainThreadQueries()
                    .build()
            }

            return instance
        }
    }
}