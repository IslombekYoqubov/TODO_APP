package uz.gita.firstlesson.todoapp.presentation.screen.main

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.viewpager2.widget.ViewPager2
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.material.tabs.TabLayoutMediator
import uz.gita.firstlesson.todoapp.R
import uz.gita.firstlesson.todoapp.databinding.ScreenMainBinding
import uz.gita.firstlesson.todoapp.presentation.adapter.TaskPagerAdapter
import uz.gita.firstlesson.todoapp.presentation.screen.add.AddTaskScreen
import uz.gita.firstlesson.todoapp.presentation.screen.deleted_screen.DeletedScreen
import java.util.UUID

class MainScreen : Fragment(R.layout.screen_main) {
    private val binding by viewBinding(ScreenMainBinding::bind)
    private val viewModel: MainViewModel by viewModels<MainViewModelImpl>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.openAddTaskScreenLiveData.observe(this, openAddTaskScreenObserver)
        viewModel.openTrashScreenLiveData.observe(this, openTrashScreenObserver)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TaskPagerAdapter(childFragmentManager, lifecycle)
        binding.pager.adapter = adapter
        TabLayoutMediator(binding.tabLayout, binding.pager) { tab, position ->
            tab.text = when (position) {
                0 -> "Todo"
                1 -> "Doing"
                else -> "Done"
            }
        }.attach()

        binding.pager.isUserInputEnabled = false
        binding.pager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.buttonAddTask.isVisible = position == 0
            }
        })

        binding.buttonAddTask.setOnClickListener {
            viewModel.openAddTaskScreen()
        }

        binding.buttonOpenTrash.setOnClickListener {
            viewModel.openTrashScreen()
        }
    }

    private val openAddTaskScreenObserver = Observer<Unit> {
        val fm  = AddTaskScreen()
        val bundle = Bundle()
        bundle.putInt("KEY", 0)
        fm.arguments = bundle
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, fm)
            .addToBackStack(UUID.randomUUID().toString())
            .commit()
    }

    private val openTrashScreenObserver = Observer<Unit>{
        parentFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, DeletedScreen())
            .addToBackStack(null)
            .commit()
    }
}