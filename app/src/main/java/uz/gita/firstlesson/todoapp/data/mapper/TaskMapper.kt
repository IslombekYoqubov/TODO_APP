package uz.gita.firstlesson.todoapp.data.mapper

import uz.gita.firstlesson.todoapp.data.model.StateEnum
import uz.gita.firstlesson.todoapp.data.model.TaskUIData
import uz.gita.firstlesson.todoapp.data.source.local.entity.TaskEntity

fun TaskEntity.toUiData() = TaskUIData(
    id = this.id,
    title = this.title,
    description = this.description,
    state = this.state.toStateEnum()
)

fun List<TaskEntity>.toUiData() = this.map { it.toUiData() }

fun TaskUIData.toEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    state = this.state.stateAmount
)


fun Int.toStateEnum() =
    when(this) {
        0 -> StateEnum.TODO
        1 -> StateEnum.DOING
        else -> StateEnum.DONE
    }