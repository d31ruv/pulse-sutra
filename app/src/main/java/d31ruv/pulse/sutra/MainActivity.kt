package d31ruv.pulse.sutra

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import d31ruv.pulse.sutra.core.data.utils.network.NetworkMonitor
import d31ruv.pulse.sutra.ui.PulseSutraApp
import d31ruv.pulse.sutra.ui.rememberPulseSutraAppState
import d31ruv.pulse.sutra.ui.theme.PulseSutraTheme
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
            val appState = rememberPulseSutraAppState(networkMonitor = networkMonitor)
            PulseSutraTheme { PulseSutraApp(appState = appState) }
        }
    }
}
