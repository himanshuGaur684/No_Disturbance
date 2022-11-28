package gaur.himanshu.wear_app.presentation.screens.home

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import gaur.himanshu.wear_app.presentation.WearTask
import gaur.himanshu.wear_app.presentation.repository.WearTaskRepository
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class WearHomeViewModel @Inject constructor(private val wearTaskRepository: WearTaskRepository) :
    ViewModel() {

    val taskList: MutableState<List<WearTask>>
        get() = mutableStateOf(wearTaskRepository.taskList.value)

    init {
        runBlocking {
            wearTaskRepository.getAllTask()
        }
    }

}