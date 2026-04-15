package d31ruv.pulse.sutra.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import d31ruv.pulse.sutra.components.PulseSutraTab
import d31ruv.pulse.sutra.core.data.utils.network.NetworkMonitor
import d31ruv.pulse.sutra.feature.chant.navigation.ChantRoute
import d31ruv.pulse.sutra.feature.journal.navigation.JournalRoute
import d31ruv.pulse.sutra.feature.settings.navigation.SettingsRoute
import d31ruv.pulse.sutra.feature.target.navigation.TargetRoute
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

@Composable
fun rememberPulseSutraAppState(
    networkMonitor: NetworkMonitor,
    navController: NavHostController = rememberNavController(),
    scope: CoroutineScope = rememberCoroutineScope(),
): PulseSutraAppState {
    return remember(scope, networkMonitor, navController) {
        PulseSutraAppState(
            scope = scope,
            networkMonitor = networkMonitor,
            navController = navController,
        )
    }
}

@Stable
class PulseSutraAppState(
    scope: CoroutineScope,
    networkMonitor: NetworkMonitor,
    val navController: NavHostController,
) {
    val isOffline = networkMonitor.isOnline.map(Boolean::not).stateIn(
        scope = scope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = false,
    )

    /**
     * Derives the currently-selected [PulseSutraTab] from the NavController's
     * back-stack, defaulting to [PulseSutraTab.Chant] when no match is found.
     */
    @Composable
    fun currentTab(): PulseSutraTab {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val destination: NavDestination? = navBackStackEntry?.destination
        return destination.asPulseSutraTab()
            ?: navController.previousBackStackEntry?.destination.asPulseSutraTab()
            ?: PulseSutraTab.Chant
    }

    @Composable
    fun isSettingsDestination(): Boolean {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        return navBackStackEntry?.destination?.hasRoute<SettingsRoute>() == true
    }

    /** Navigates to the destination associated with [tab]. */
    fun selectTab(tab: PulseSutraTab) {
        val route: Any = when (tab) {
            PulseSutraTab.Chant -> ChantRoute
            PulseSutraTab.Target -> TargetRoute
            PulseSutraTab.Journal -> JournalRoute
        }
        navController.navigate(route) {
            // Pop up to the start destination, keeping back-stack clean
            popUpTo(navController.graph.startDestinationId) {
                saveState = true
            }
            launchSingleTop = true
            restoreState = true
        }
    }

    fun toggleSettings(checked: Boolean) {
        if (checked) {
            navController.navigate(SettingsRoute) { launchSingleTop = true }
        } else navController.popBackStack()
    }

    private fun NavDestination?.asPulseSutraTab(): PulseSutraTab? = when {
        this?.hasRoute<ChantRoute>() == true -> PulseSutraTab.Chant
        this?.hasRoute<TargetRoute>() == true -> PulseSutraTab.Target
        this?.hasRoute<JournalRoute>() == true -> PulseSutraTab.Journal
        else -> null
    }
}
