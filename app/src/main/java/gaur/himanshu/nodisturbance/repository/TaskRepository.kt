package gaur.himanshu.nodisturbance.repository

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.model.Document
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.room.TaskDAO
import kotlinx.coroutines.*

class TaskRepository(private val taskDAO: TaskDAO, private val firestore: FirebaseFirestore) {

    private val scope = CoroutineScope(Dispatchers.IO)

    private val _taskList = mutableStateOf(listOf<Task>())
    val taskList: State<List<Task>>
        get() = _taskList


    suspend fun insertOrUpdateTask(task: Task) {
        withContext(Dispatchers.IO) {
            val collection = firestore.collection("activity")
            val document = collection.document()
            val id = document.id
            task.id = id
            collection.add(task).addOnSuccessListener {

            }

        }
    }


    suspend fun deleteTask(task: Task) {
        Log.d("TAG", "deleteTask: ")
        firestore.collection("activity").addSnapshotListener { value, error ->

            value?.documents?.forEach {
                val remoteTask = it.toObject(Task::class.java)
                if (remoteTask?.id == task.id) {
                    firestore.collection("activity").document(it.id).delete().addOnSuccessListener {
                        scope.launch {
                            taskDAO.delete(task)
                            getAllTask()
                        }
                    }.addOnFailureListener {
                        Log.d("TAG", "deleteTask: Failure")
                    }
                }
            }


        }
//
//        firestore.collection("activity").document(task.id).delete().addOnSuccessListener {
//            scope.launch {
//                taskDAO.delete(task)
//                getAllTask()
//            }
//
//        }
    }

    suspend fun updateTask(task: Task) {

        firestore.collection("activity").addSnapshotListener { value, error ->

            value?.documents?.forEach {
                val remoteTask = it.toObject(Task::class.java)
                if (remoteTask?.id == task.id) {
                    firestore.collection("activity").document(it.id).update(
                        mapOf(
                            "id" to task.id,
                            "activityName" to task.activityName,
                            "relatedActivity" to task.relatedActivity
                        )
                    ).addOnSuccessListener {
                        scope.launch {
                            taskDAO.insert(task)
                            getAllTask()
                        }
                    }.addOnFailureListener {
                        Log.d("TAG", "deleteTask: Failure")
                    }
                }
            }


        }
//
//        firestore.collection("activity").document(task.id).update(
//            mapOf(
//                "id" to task.id,
//                "activityName" to task.activityName,
//                "relatedActivity" to task.relatedActivity
//            )
//        ).addOnSuccessListener {
//            scope.launch {
//                taskDAO.insert(task)
//                getAllTask()
//            }
//        }
    }

    suspend fun getAllTask() {
        val list = mutableSetOf<Task>()
        firestore.collection("activity").addSnapshotListener { value, error ->
            value?.documents?.forEach {
                it.toObject(Task::class.java)?.let { it1 -> list.add(it1) }
            }
            scope.launch {
                list.forEach {
                    taskDAO.insert(it)
                }
                _taskList.value = taskDAO.getAllTask()
            }
        }
    }

    fun onDestroy() {
        scope.cancel()
    }

}