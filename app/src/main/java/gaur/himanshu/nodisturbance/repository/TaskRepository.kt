package gaur.himanshu.nodisturbance.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.room.TaskDAO
import kotlinx.coroutines.*

class TaskRepository(private val taskDAO: TaskDAO, private val firestore: FirebaseFirestore) {


    private val _taskList = mutableStateOf(listOf<Task>())
    val taskList: State<List<Task>>
        get() = _taskList


    suspend fun insertOrUpdateTask(task: Task) {
        withContext(Dispatchers.IO) {
            val collection = firestore.collection("activity")
            val id = collection.document().id
            task.id = id
            collection.add(task).addOnSuccessListener {

            }

        }
    }

    suspend fun deleteTask(task: Task) {
        taskDAO.delete(task)
    }

    suspend fun getAllTask() {
        val list = mutableSetOf<Task>()
        _taskList.value = taskDAO.getAllTask()
        firestore.collection("activity").addSnapshotListener { value, error ->
            value?.documents?.forEach {
                it.toObject(Task::class.java)?.let { it1 -> list.add(it1) }
            }
            CoroutineScope(Dispatchers.IO).launch {
                list.forEach {
                    taskDAO.insert(it)
                }
                _taskList.value = taskDAO.getAllTask()
            }
        }
    }

}