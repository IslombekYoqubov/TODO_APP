package uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.firstlesson.todoapp.R
import uz.gita.firstlesson.todoapp.data.mapper.toUiData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.databinding.TrashScreenBinding
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl
import uz.gita.firstlesson.todoapp.presentation.adapter.TaskAdapter
import uz.gita.firstlesson.todoapp.presentation.factories.DeletedViewModelFactory

class DeletedScreen : Fragment(R.layout.trash_screen) {
    private val adapter = TaskAdapter()
    private val viewModel : DeletedViewModel by viewModels {
        DeletedViewModelFactory(AppRepositoryImpl.getInstance())
    }
    private val binding by viewBinding(TrashScreenBinding::bind)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonBack.setOnClickListener { parentFragmentManager.popBackStack() }
        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        viewModel.liveDataEntity.observe(viewLifecycleOwner, liveDataObserver)
        adapter.setOnLongClickListener {
            showTaskDialog(it)
        }
    }

    private val liveDataObserver = Observer<List<TaskEntity>>{
        adapter.submitList(it.toUiData())
        binding.emptyContainer.isVisible = it.isEmpty()
    }

    @SuppressLint("MissingInflatedId")
    private fun showTaskDialog(task: TaskUIData) {
        val dialogView = layoutInflater.inflate(R.layout.item_longclick_dialog, null)

        val deleteButton = dialogView.findViewById<TextView>(R.id.delete_item)
        val edit = dialogView.findViewById<TextView>(R.id.edit_item)
        edit.text = "Restore Item"
        val close = dialogView.findViewById<TextView>(R.id.close_dialog)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        deleteButton.setOnClickListener {
            viewModel.deleteItem(task.id)
            dialog.dismiss()
        }

        edit.setOnClickListener {
            viewModel.restoreItem(task.id)
            dialog.dismiss()
        }

        close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}