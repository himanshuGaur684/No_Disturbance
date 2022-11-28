package gaur.himanshu.nodisturbance.screens

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.manualcomposablecalls.composable
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.dependency
import gaur.himanshu.nodisturbance.components.BottomBar
import gaur.himanshu.nodisturbance.screens.destinations.*
import gaur.himanshu.nodisturbance.screens.home.HomeScreen
import gaur.himanshu.nodisturbance.screens.home.HomeViewModel
import gaur.himanshu.nodisturbance.screens.info.InfoScreen
import gaur.himanshu.nodisturbance.screens.report.ReportScreen
import gaur.himanshu.nodisturbance.screens.settings.SettingScreen
import gaur.himanshu.nodisturbance.screens.timer.TimerScreen

@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomBar(
                navController = navController
            )
        }) {
        DestinationsNavHost(navController = navController, navGraph = NavGraphs.root) {
            composable(HomeScreenDestination) {
                val viewModel = hiltViewModel<HomeViewModel>()
                HomeScreen(homeViewModel = viewModel, destinationsNavigator) { t, task ->
                    t.navigate(TaskDetailScreenDestination(task = task).route)
                }
            }
            composable(InfoScreenDestination) {
                InfoScreen()
            }
            composable(ReportScreenDestination) {
                ReportScreen()
            }
            composable(SettingScreenDestination) {
                SettingScreen()
            }
            composable(TimerScreenDestination) {
                TimerScreen(task = navArgs.task)
            }
        }
        Log.d("TAG", "MainScreen: $it")
    }

}