package uz.gita.firstlesson.todoapp.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.presentation.page.todo.TodoViewModelImpl

class TodoViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModelImpl::class.java)) {
            return TodoViewModelImpl(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
