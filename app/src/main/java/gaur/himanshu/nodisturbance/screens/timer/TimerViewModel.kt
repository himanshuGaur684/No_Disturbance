package gaur.himanshu.nodisturbance.screens.timer

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.repository.TaskRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class TimerViewModel @Inject constructor(private val taskRepository: TaskRepository): ViewModel() {

    val taskList : MutableState<List<Task>>
    get() = mutableStateOf(taskRepository.taskList.value)

    init {
        runBlocking {
            taskRepository.getAllTask()
        }
    }

}