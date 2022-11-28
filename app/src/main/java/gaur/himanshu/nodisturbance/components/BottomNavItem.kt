package gaur.himanshu.nodisturbance.components

import androidx.annotation.DrawableRes
import com.ramcosta.composedestinations.spec.DirectionDestinationSpec
import com.ramcosta.composedestinations.utils.startDestination
import gaur.himanshu.nodisturbance.R
import gaur.himanshu.nodisturbance.screens.destinations.*

sealed class BottomNavItem(
    val destination: String,
    @DrawableRes val icon: Int,
    val label: String
) {
    object Home : BottomNavItem(
        destination = HomeScreenDestination.route,
        icon = R.drawable.ic_home,
        label = "Home"
    )

    object Timer : BottomNavItem(
        destination = TimerScreenDestination.route,
        icon = R.drawable.ic_timer,
        label = "Timer"
    )

    object Reports : BottomNavItem(
        destination = ReportScreenDestination.route,
        icon = R.drawable.ic_report,
        label = "Reports"
    )

    object Setting :
        BottomNavItem(
            destination = SettingScreenDestination.route,
            icon = R.drawable.ic_settings,
            label = "Settings"
        )

    object Info : BottomNavItem(
        destination = InfoScreenDestination.route,
        icon = R.drawable.ic_info,
        label = "Info"
    )
}
