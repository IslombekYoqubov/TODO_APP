package uz.gita.firstlesson.todoapp.presentation.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.gita.firstlesson.todoapp.presentation.page.doing.DoingPage
import uz.gita.firstlesson.todoapp.presentation.page.done.DonePage
import uz.gita.firstlesson.todoapp.presentation.page.todo.TodoPage

class TaskPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fm, lifecycle) {
    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> TodoPage()
        1 -> DoingPage()
        else -> DonePage()
    }

    override fun getItemCount(): Int = 3
}


