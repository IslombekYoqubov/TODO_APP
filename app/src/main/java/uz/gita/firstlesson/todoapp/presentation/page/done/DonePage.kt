package uz.gita.firstlesson.todoapp.presentation.page.done

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import uz.gita.firstlesson.todoapp.R
import uz.gita.firstlesson.todoapp.data.mapper.toUiData
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity
import uz.gita.firstlesson.todoapp.databinding.PageDoneBinding
import uz.gita.firstlesson.todoapp.domain.AppRepositoryImpl
import uz.gita.firstlesson.todoapp.presentation.adapter.TaskAdapter
import uz.gita.firstlesson.todoapp.presentation.factories.DoneViewModelFactory
import uz.gita.firstlesson.todoapp.presentation.page.done.DoneViewModel
import uz.gita.firstlesson.todoapp.presentation.page.done.DoneViewModelImpl
import uz.gita.firstlesson.todoapp.presentation.screen.add.AddTaskScreen
import kotlin.getValue

class DonePage : Fragment(R.layout.page_done) {
    private val binding by viewBinding(PageDoneBinding::bind)
    private val viewModel: DoneViewModel by viewModels {
        DoneViewModelFactory(AppRepositoryImpl.getInstance())
    }
    private val adapter by lazy { TaskAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val touchHelper = ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT){
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val task = adapter.currentList[viewHolder.absoluteAdapterPosition]
                viewModel.updateStatus(task.id, 1)
            }
        })
        touchHelper.attachToRecyclerView(binding.list)

        viewModel.emptyStateLiveData.observe(viewLifecycleOwner, emptyStateObserver)
        viewModel.tasksLiveData.observe(viewLifecycleOwner, tasksObserver)
        viewModel.taskEntityLiveData.observe(viewLifecycleOwner, taskLiveDataObserver)

        binding.list.adapter = adapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        adapter.setOnLongClickListener { showTaskDialog(it) }
    }

    private val emptyStateObserver = Observer<Boolean> {
        binding.emptyContainer.isVisible = it
    }

    private val tasksObserver = Observer<List<TaskUIData>> {
        adapter.submitList(it)
    }

    private val taskLiveDataObserver = Observer<List<TaskEntity>>{
        adapter.submitList(it.toUiData())
        binding.emptyContainer.isVisible = it.isEmpty()
    }

    @SuppressLint("MissingInflatedId")
    private fun showTaskDialog(task: TaskUIData) {
        val dialogView = layoutInflater.inflate(R.layout.item_longclick_dialog, null)

        val deleteButton = dialogView.findViewById<TextView>(R.id.delete_item)
        val edit = dialogView.findViewById<TextView>(R.id.edit_item)
        val close = dialogView.findViewById<TextView>(R.id.close_dialog)

        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        deleteButton.setOnClickListener {
            viewModel.moveToTrash(task.id)
            dialog.dismiss()
        }

        edit.setOnClickListener {
            val args = Bundle()
            args.putInt("KEY", task.id)
            val fr = AddTaskScreen()
            fr.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.mainContainer, fr)
                .addToBackStack(null)
                .commit()

            dialog.dismiss()
        }

        close.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}