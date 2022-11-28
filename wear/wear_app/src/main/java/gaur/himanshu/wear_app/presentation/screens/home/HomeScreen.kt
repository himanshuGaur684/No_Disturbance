package gaur.himanshu.wear_app.presentation.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.wear.compose.material.*
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gaur.himanshu.wear_app.presentation.WearTask

@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    homeViewModel: WearHomeViewModel = hiltViewModel()
) {
    val taskList = homeViewModel.taskList.value

    Column(horizontalAlignment = CenterHorizontally) {
        Text(
            text = "Your Activities",
            style = MaterialTheme.typography.caption2,
            modifier = Modifier
                .padding(top = 12.dp, bottom = 4.dp)
        )

        Spacer(modifier = Modifier.height(4.dp))

        ScalingLazyColumn(
            modifier = Modifier.fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(taskList) {
                WearTaskListItem(wearTask = it)
            }
        }
    }

}

@Composable
fun WearTaskListItem(wearTask: WearTask) {

    Card(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        onClick = {

        }) {

        Column(modifier = Modifier.padding(2.dp)) {
            Text(text = wearTask.activityName, style = MaterialTheme.typography.body2)
            Text(text = wearTask.relatedActivity, style = MaterialTheme.typography.body2)
        }
    }

}
