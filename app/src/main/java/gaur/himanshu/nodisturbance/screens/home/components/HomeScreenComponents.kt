package gaur.himanshu.nodisturbance.screens.home.components

import android.service.notification.NotificationListenerService
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import gaur.himanshu.nodisturbance.R
import gaur.himanshu.nodisturbance.model.Task

@Composable
fun AddTaskDialog(
    isEdit: Boolean = false,
    task: Task? = null,
    showDialog: (Boolean) -> Unit,
    saveTask: (String, String, String?, Boolean) -> Unit
) {
    val context = LocalContext.current
    val activityName = remember { mutableStateOf(task?.activityName ?: "") }
    val relatedActivity = remember { mutableStateOf(task?.relatedActivity ?: "") }

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
                                        relatedActivity.value,
                                        task?.id,
                                        isEdit
                                    )
                                    showDialog.invoke(false)
                                }
                            }

                        }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                    ) {
                        Text(text = if (isEdit) "Update Task" else "Add Task")
                    }
                }
            }
        }
    }
}

@Composable
fun TaskListItem(task: Task, onClick: (Task) -> Unit, delete: () -> Unit, edit: () -> Unit) {

    Surface(modifier = Modifier.padding(8.dp)) {
        Card(
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth(), elevation = 4.dp
        ) {
            Row(
                modifier = Modifier.padding(12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(modifier = Modifier
                    .fillMaxWidth(.94f)
                    .clickable {
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
                PopUpDialog(edit = {
                    edit.invoke()
                }, delete = {
                    delete.invoke()
                })
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


@Composable
fun PopUpDialog(edit: () -> Unit, delete: () -> Unit) {
    val openPopUp = remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.background(color = Color.White)) {
        Icon(
            painter = painterResource(id = R.drawable.vertical_menu),
            contentDescription = null,
            modifier = Modifier
                .width(20.dp)
                .height(20.dp)
                .clickable {
                    openPopUp.value = !openPopUp.value
                })
        if (openPopUp.value) {
            Popup(
                alignment = Alignment.TopStart,
                properties = PopupProperties(
                    dismissOnClickOutside = true,
                    dismissOnBackPress = true
                ), onDismissRequest = {
                    openPopUp.value = !openPopUp.value
                }
            ) {

                Box(
                    modifier = Modifier
                        .wrapContentWidth()
                        .border(
                            border = BorderStroke(.1.dp, Color.Gray),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .background(color = Color.White),
                ) {

                    Column(modifier = Modifier.padding(8.dp)) {

                        Row(modifier = Modifier
                            .clickable {
                                edit.invoke()
                                openPopUp.value = !openPopUp.value
                            }
                            .padding(8.dp)) {
                            Icon(
                                imageVector = Icons.Default.Edit,
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(text = "Edit", modifier = Modifier.padding(horizontal = 8.dp))
                        }
                        Row(modifier = Modifier
                            .clickable {
                                delete.invoke()
                                openPopUp.value = !openPopUp.value
                            }
                            .padding(8.dp)) {
                            Icon(
                                imageVector = Icons.Default.Delete,
                                contentDescription = null,
                                modifier = Modifier.padding(horizontal = 4.dp)
                            )
                            Text(text = "Delete", modifier = Modifier.padding(horizontal = 8.dp))
                        }
                    }
                }

            }
        }
    }


}


class CloseNotification : NotificationListenerService() {

}

