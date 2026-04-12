package d31ruv.diecast.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import d31ruv.diecast.core.data.utils.network.NetworkMonitor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberDieAppState(
    networkMonitor: NetworkMonitor,
    scope: CoroutineScope = rememberCoroutineScope(),
): DieAppState {
    return remember(scope, networkMonitor) {
        DieAppState(scope = scope, networkMonitor = networkMonitor)
    }
}

@Stable
class DieAppState(scope: CoroutineScope, networkMonitor: NetworkMonitor) {
    val isOffline = networkMonitor.isOnline.map(Boolean::not).stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )
}