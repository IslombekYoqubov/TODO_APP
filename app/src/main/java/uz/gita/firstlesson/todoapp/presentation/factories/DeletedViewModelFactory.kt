package uz.gita.firstlesson.todoapp.presentation.factories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.gita.firstlesson.todoapp.domain.AppRepository
import uz.gita.firstlesson.todoapp.presentation.page.doing.DoingViewModelImpl
import uz.gita.firstlesson.todoapp.presentation.page.todo.TodoViewModelImpl
import uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen.DeletedViewModelImpl

class DeletedViewModelFactory(
    private val repository: AppRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DeletedViewModelImpl::class.java)) {
            return DeletedViewModelImpl(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
