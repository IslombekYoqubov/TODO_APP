package uz.gita.firstlesson.todoapp.presentation.screen.add

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.firstlesson.todoapp.R
import uz.gita.firstlesson.todoapp.databinding.ScreenAddTaskBinding
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl
import uz.gita.firstlesson.todoapp.presentation.factories.AddTaskViewModelFactory

class AddTaskScreen : Fragment(R.layout.screen_add_task) {
    private val binding by viewBinding(ScreenAddTaskBinding::bind)
    private val taskId : Int by lazy { (arguments?.getInt("KEY") ?:0)}
    private val viewModel: AddTaskViewModel by viewModels {
        AddTaskViewModelFactory(AppRepositoryImpl.getInstance())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        load()
        binding.editTitle.addTextChangedListener {
            viewModel.setTitle(it.toString())
        }

        binding.editDescription.addTextChangedListener {
            viewModel.setDescription(it.toString())
        }
        binding.buttonAddTask.setOnClickListener {
            viewModel.insert(taskId, binding.editTitle.text.toString(), binding.editDescription.text.toString())
        }

        viewModel.buttonStateLiveData.observe(viewLifecycleOwner, buttonStateObserver)
        viewModel.successLiveData.observe(viewLifecycleOwner, successObserver)
        viewModel.finishScreenLiveData.observe(viewLifecycleOwner, finishScreenObserver)
    }

    private val buttonStateObserver = Observer<Boolean> {
        binding.buttonAddTask.isEnabled = it
    }

    private val successObserver = Observer<String> {
        Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
    }

    private val finishScreenObserver = Observer<Unit> {
        parentFragmentManager.popBackStack()
    }
    private fun load(){
        if(taskId > 0) {
            binding.buttonAddTask.text = "Edit Task"
            binding.title.text = "Edit"
            val entity = viewModel.getItemById(taskId)
            viewModel.setTitle(entity.title)
            viewModel.setDescription(entity.description)
            binding.editDescription.setText(entity.description)
            binding.editTitle.setText(entity.title)
        }
    }
}





