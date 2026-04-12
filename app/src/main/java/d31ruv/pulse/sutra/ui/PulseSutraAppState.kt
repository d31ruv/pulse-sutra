package d31ruv.pulse.sutra.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import d31ruv.pulse.sutra.core.data.utils.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberPulseSutraAppState(
    networkMonitor: NetworkMonitor,
    scope: CoroutineScope = rememberCoroutineScope(),
): PulseSutraAppState {
    return remember(scope, networkMonitor) {
        PulseSutraAppState(scope = scope, networkMonitor = networkMonitor)
    }
}

@Stable
class PulseSutraAppState(scope: CoroutineScope, networkMonitor: NetworkMonitor) {
    val isOffline = networkMonitor.isOnline.map(Boolean::not).stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )
}
