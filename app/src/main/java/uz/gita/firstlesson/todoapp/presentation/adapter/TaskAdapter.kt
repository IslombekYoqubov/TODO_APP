package uz.gita.firstlesson.todoapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.databinding.ItemTaskBinding

class TaskAdapter : ListAdapter<TaskUIData, TaskAdapter.TaskViewHolder>(TaskDiffUtil) {
    private var selectItemListener: ((TaskUIData) -> Unit)?= null
    private var longClickListener: ((TaskUIData) -> Unit)? = null

    fun setOnLongClickListener(block: (TaskUIData) -> Unit) {
        longClickListener = block
    }

    object TaskDiffUtil: DiffUtil.ItemCallback<TaskUIData>() {
        override fun areItemsTheSame(oldItem: TaskUIData, newItem: TaskUIData): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TaskUIData, newItem: TaskUIData): Boolean {
            return oldItem == newItem
        }
    }

    inner class TaskViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                selectItemListener?.invoke(getItem(absoluteAdapterPosition))
            }
            binding.root.setOnLongClickListener {
                longClickListener?.invoke(getItem(absoluteAdapterPosition))
                true
            }
        }

        fun bind() {
            getItem(absoluteAdapterPosition).apply {
                binding.textTitle.text = title
                binding.textDescription.text = description
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        TaskViewHolder(ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind()
    }

    fun setSelectItemListener(block: (TaskUIData) -> Unit) {
        selectItemListener = block
    }
}

