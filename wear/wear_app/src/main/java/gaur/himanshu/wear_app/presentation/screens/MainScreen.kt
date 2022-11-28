package gaur.himanshu.wear_app.presentation.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.Button
import androidx.wear.compose.material.MaterialTheme
import androidx.wear.compose.material.ScalingLazyColumn
import androidx.wear.compose.material.Text
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import gaur.himanshu.wear_app.presentation.screens.destinations.HomeScreenDestination
import gaur.himanshu.wear_app.presentation.screens.destinations.ReportScreenDestination
import gaur.himanshu.wear_app.presentation.screens.destinations.TimerScreenDestination


@Destination(start = true)
@Composable
fun WearMainScreen(navigator: DestinationsNavigator) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp), contentAlignment = Alignment.Center
    ) {
        ScalingLazyColumn(
            modifier = Modifier,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Button(modifier = Modifier
                    .fillMaxWidth(.8f)
                    .wrapContentHeight()
                    .align(Alignment.Center),
                    onClick = {
                        navigator.navigate(HomeScreenDestination)
                    }) {
                    Text(
                        text = "HOME", Modifier.padding(horizontal = 8.dp),
                        style = MaterialTheme.typography.caption1
                    )
                }
            }
            item {
                Button(modifier = Modifier
                    .fillMaxWidth(.8f)
                    .wrapContentHeight()
                    .align(Alignment.Center),
                    onClick = {
                        navigator.navigate(TimerScreenDestination)
                    }) {
                    Text(
                        text = "TIMER",
                        modifier = Modifier,
                        style = MaterialTheme.typography.caption1
                    )
                }

            }
            item {
                Button(modifier = Modifier
                    .fillMaxWidth(.8f)
                    .wrapContentHeight()
                    .align(Alignment.Center),
                    onClick = {
                        navigator.navigate(ReportScreenDestination)
                    }) {
                    Text(
                        text = "REFLECTION",
                        Modifier,
                        style = MaterialTheme.typography.caption1
                    )
                }
            }
        }
    }


}
