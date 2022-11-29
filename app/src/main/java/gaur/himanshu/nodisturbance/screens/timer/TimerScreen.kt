package gaur.himanshu.nodisturbance.screens.timer

import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import gaur.himanshu.nodisturbance.model.Task
import gaur.himanshu.nodisturbance.screens.home.components.CustomCheckBoxes
import kotlin.concurrent.timer

@Destination
@Composable
fun TimerScreen(timerViewModel: TimerViewModel = hiltViewModel(), task: Task? = null) {

    Scaffold(topBar = {TopAppBar(title = { Text(text = "Timer")})}) {
        Log.d("TAG", "TimerScreen: $it")
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            Text(text = "Timer Screen")
        }
    }


}
