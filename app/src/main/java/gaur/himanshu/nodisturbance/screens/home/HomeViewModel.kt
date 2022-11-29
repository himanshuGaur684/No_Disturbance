package gaur.himanshu.nodisturbance.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.repository.TaskRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val taskRepository: TaskRepository) : ViewModel() {


    val taskList :MutableState<List<Task>>
    get() =   mutableStateOf(taskRepository.taskList.value)

    init {
      runBlocking {
          taskRepository.getAllTask()
      }
    }


    fun insertOrUpdateTask(task:Task)=viewModelScope.launch{
        taskRepository.insertOrUpdateTask(task)
    }

    fun updateTask(task:Task)=viewModelScope.launch {
        taskRepository.updateTask(task)
    }

    fun deleteTask(task: Task)=viewModelScope.launch {
        taskRepository.deleteTask(task)
    }

    override fun onCleared() {
        super.onCleared()
        taskRepository.onDestroy()
    }

}