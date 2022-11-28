package gaur.himanshu.nodisturbance.screens.home.components

import android.service.notification.NotificationListenerService
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import gaur.himanshu.nodisturbance.model.Task

@Composable
fun AddTaskDialog(
    showDialog: (Boolean) -> Unit,
    saveTask: (String, String) -> Unit
) {
    val context = LocalContext.current
    val activityName = remember { mutableStateOf("") }
    val relatedActivity = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = { showDialog(false) }, properties = DialogProperties(
            dismissOnBackPress = true, dismissOnClickOutside = true
        )
    ) {
        Surface(modifier = Modifier.wrapContentSize(), shape = RoundedCornerShape(size = 12.dp)) {
            Box(modifier = Modifier.padding(12.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {

                    TextField(colors = TextFieldDefaults.textFieldColors(
                        backgroundColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                        placeholder = { Text("Activity Name") },
                        value = activityName.value,
                        onValueChange = {
                            activityName.value = it
                        })
                    Spacer(modifier = Modifier.height(12.dp))
                    TextField(
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.Transparent,
                            focusedIndicatorColor = Color.Gray,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        placeholder = { Text("Related Activities") },
                        value = relatedActivity.value,
                        onValueChange = {
                            relatedActivity.value = it
                        })
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = {
                            when {
                                activityName.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Activity Name must be filled",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                relatedActivity.value.isEmpty() -> {
                                    Toast.makeText(
                                        context,
                                        "Related Activity must be filled",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                }
                                else -> {
                                    saveTask.invoke(
                                        activityName.value,
                                        relatedActivity.value
                                    )
                                    showDialog.invoke(false)
                                }
                            }

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = "Add Task")
                    }

                }
            }
        }

    }
}

@Composable
fun TaskListItem(task: Task,onClick:(Task)->Unit) {

    Surface(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(), elevation = 4.dp
        ) {
            Column(modifier = Modifier.padding(12.dp).clickable {
                onClick.invoke(task)
            }) {
                Text(
                    text = task.activityName,
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(top = 4.dp, bottom = 2.dp)
                )
                Text(
                    text = task.relatedActivity,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(bottom = 4.dp)
                )

            }
        }

    }

}

@Composable
fun CustomCheckBoxes(level: String, checkChanged: (Boolean) -> Unit) {
    val checked = remember {
        mutableStateOf(false)
    }
    Row(
        modifier = Modifier
            .wrapContentSize(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(checked = checked.value, onCheckedChange = {
            checked.value = it
            checkChanged.invoke(checked.value)
        })
        Text(text = level)
    }

}


class CloseNotification : NotificationListenerService() {


}

