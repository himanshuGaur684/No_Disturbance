package gaur.himanshu.nodisturbance.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.Navigation
import com.ramcosta.composedestinations.DestinationsNavHost
import gaur.himanshu.nodisturbance.screens.NavGraphs

@Composable
fun Navigation() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}


const val BOTTOM_NAV_GRAPH="bottom_navigation"