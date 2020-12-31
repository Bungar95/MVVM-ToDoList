package ungar.todolist.ui.tasks

import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ungar.todolist.R

@AndroidEntryPoint
class TasksFragment: Fragment(R.layout.fragment_tasks) {

    private val viedModel: TasksViewModel by viewModels()
}