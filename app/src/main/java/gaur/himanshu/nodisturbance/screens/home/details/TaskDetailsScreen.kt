package gaur.himanshu.nodisturbance.screens.home.details

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ramcosta.composedestinations.annotation.Destination
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.screens.home.components.CustomCheckBoxes


@Destination
@Composable
fun TaskDetailScreen(task: Task) {

    Scaffold(
        modifier = Modifier.padding(bottom = 55.dp),
        topBar = { TopAppBar(title = { Text(text = "Task Details") }) }) {
        Log.d("TAG", "TimerScreen: $it")
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
        ) {
            Column {
                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Activity Name",
                    style = MaterialTheme.typography.h6
                )
                Text(
                    modifier = Modifier.padding(bottom = 12.dp, top = 2.dp),
                    text = task.activityName,
                    style = MaterialTheme.typography.body1
                )

                Divider(modifier = Modifier)

                Text(
                    modifier = Modifier.padding(vertical = 8.dp),
                    text = "Related Activities",
                    style = MaterialTheme.typography.h6
                )

                Text(
                    modifier = Modifier.padding(bottom = 12.dp, top = 2.dp),
                    text = task.relatedActivity,
                    style = MaterialTheme.typography.body1
                )

                Divider()

                Text(
                    text = "Choose your Level",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.padding(vertical = 12.dp)
                )

                CustomCheckBoxes(
                    level = "Level 1 ( Stop notification from social network )",
                    checkChanged = {

                    })
                CustomCheckBoxes(
                    level = "Level 2 ( Stop all Apps notifications )",
                    checkChanged = {

                    })
                CustomCheckBoxes(
                    level = "Level 3 ( Stop notifications, mails etc. )",
                    checkChanged = {

                    })


            }
        }
    }

}
