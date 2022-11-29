package gaur.himanshu.nodisturbance.screens.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.navArgument
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.screens.destinations.TaskDetailScreenDestination
import gaur.himanshu.nodisturbance.screens.destinations.TimerScreenDestination
import gaur.himanshu.nodisturbance.screens.home.components.AddTaskDialog
import gaur.himanshu.nodisturbance.screens.home.components.TaskListItem


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Destination(start = true)
@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel,
    navigator: DestinationsNavigator,
    navigate: (DestinationsNavigator, Task) -> Unit
) {
    val showDialog = remember { mutableStateOf(false) }
    val isEdit = remember {
        mutableStateOf(false)
    }
    val task = remember {
        mutableStateOf(Task("", ""))
    }

    Scaffold(modifier = Modifier.padding(bottom = 55.dp), topBar = {
        TopAppBar(title = { Text(text = "Focus Work") })
    },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                showDialog.value = !showDialog.value
            }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (showDialog.value) {
                AddTaskDialog(
                    isEdit = isEdit.value,
                    task = if (isEdit.value) task.value else null,
                    showDialog = {
                        showDialog.value = it
                    },
                    { activityName, relatedActivity, taskId, isEdit ->
                        if (isEdit) {
                            homeViewModel.updateTask(
                                Task(
                                    activityName = activityName,
                                    relatedActivity = relatedActivity,
                                    id = taskId.toString()
                                )
                            )
                        } else {
                            homeViewModel.insertOrUpdateTask(
                                Task(
                                    activityName = activityName, relatedActivity = relatedActivity
                                )
                            )
                        }

                    })
            }
            LazyColumn {
                items(homeViewModel.taskList.value) {
                    TaskListItem(task = it, edit = {
                        showDialog.value = !showDialog.value
                        isEdit.value = !isEdit.value
                        task.value = it
                    }, delete = {
                        Log.d("TAG", "HomeScreen: delete")
                        homeViewModel.deleteTask(it)
                    }, onClick = {
                        navigate.invoke(navigator, it)
                    })
                }
            }
        }
    }

}