package gaur.himanshu.wear_app.presentation.repository

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import com.google.firebase.firestore.FirebaseFirestore
import gaur.himanshu.wear_app.presentation.WearTask
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WearTaskRepository(private val firestore: FirebaseFirestore) {


    private val _taskList = mutableStateOf(listOf<WearTask>())
    val taskList: State<List<WearTask>>
        get() = _taskList


    suspend fun insertOrUpdateTask(task: WearTask) {
        withContext(Dispatchers.IO) {
            val collection = firestore.collection("activity")
            val id = collection.document().id
            task.id = id
            collection.add(task).addOnSuccessListener {

            }

        }
    }

//    suspend fun deleteTask(task: Task) {
//        taskDAO.delete(task)
//    }

    suspend fun getAllTask() {
        val list = mutableSetOf<WearTask>()
        firestore.collection("activity").addSnapshotListener { value, error ->
            value?.documents?.forEach {
                it.toObject(WearTask::class.java)?.let { it1 -> list.add(it1) }
                _taskList.value = list.toList()
            }
//            CoroutineScope(Dispatchers.IO).launch {
//                list.forEach {
//                    taskDAO.insert(it)
//                }
//                _taskList.value = taskDAO.getAllTask()
//            }
        }
    }

}