package uz.gita.firstlesson.todoapp.app

import android.app.Application
import android.content.Context
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl

class MyApp : Application() {
    companion object {
        lateinit var appContext: Context
    }

    override fun onCreate() {
        super.onCreate()
        appContext = this
        AppRepositoryImpl.init()
    }
}

