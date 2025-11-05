package uz.gita.firstlesson.todoapp.data.model

data class TaskUIData(
    val id: Int,
    val title: String,
    val description: String,
    val state: StateEnum
)

