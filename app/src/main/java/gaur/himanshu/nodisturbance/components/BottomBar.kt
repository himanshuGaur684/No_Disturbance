package gaur.himanshu.nodisturbance.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.ramcosta.composedestinations.navigation.navigate
import gaur.himanshu.nodisturbance.screens.NavGraphs
import gaur.himanshu.nodisturbance.screens.appCurrentDestinationAsState
import gaur.himanshu.nodisturbance.screens.startAppDestination

@Composable
fun BottomBar(
    navController: NavController
) {

    val currentDestination =
        navController.appCurrentDestinationAsState().value ?: NavGraphs.root.startAppDestination

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Timer,
        BottomNavItem.Reports,
        BottomNavItem.Setting,
        BottomNavItem.Info
    )

    BottomNavigation {
        items.forEach { item ->
            BottomNavigationItem(
                label = { Text(text = item.label) },
                alwaysShowLabel = true,
                selected = item.destination == currentDestination.route,
                onClick = {
                    navController.navigate(item.destination)
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = null
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.White
            )
        }
    }

}