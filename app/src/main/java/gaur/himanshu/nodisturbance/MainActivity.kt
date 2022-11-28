package gaur.himanshu.nodisturbance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import gaur.himanshu.nodisturbance.screens.MainScreen
import gaur.himanshu.nodisturbance.ui.theme.NoDisturbanceTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NoDisturbanceTheme {
               MainScreen()
            }
        }
    }
}
