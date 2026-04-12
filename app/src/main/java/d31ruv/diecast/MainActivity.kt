package d31ruv.diecast

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import d31ruv.diecast.core.data.utils.network.NetworkMonitor
import d31ruv.diecast.ui.DiecastApp
import d31ruv.diecast.ui.rememberDieAppState
import d31ruv.diecast.ui.theme.DiecastTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var networkMonitor: NetworkMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val appState = rememberDieAppState(networkMonitor = networkMonitor)
            DiecastTheme { DiecastApp(appState = appState) }
        }
    }
}