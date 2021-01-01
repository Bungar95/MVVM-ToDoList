package ungar.todolist.ui.tasks

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import ungar.todolist.data.TaskDao

class TasksViewModel @ViewModelInject constructor(
    private val taskDao: TaskDao
) : ViewModel() {

    val searchQuery = MutableStateFlow("") // mutablestateflow has a single mutable value

    private val tasksFlow = searchQuery.flatMapLatest {
        taskDao.getTasks(it)
    }

    val tasks = tasksFlow.asLiveData()
}