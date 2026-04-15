package d31ruv.pulse.sutra.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.exclude
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBars
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScaffoldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.components.BackgroundOrbs
import d31ruv.pulse.sutra.components.DashboardTopBar
import d31ruv.pulse.sutra.components.OfflineBadge
import d31ruv.pulse.sutra.components.PulseSutraBottomBar
import d31ruv.pulse.sutra.navigation.PulseSutraNavGraph

@Composable
fun PulseSutraApp(appState: PulseSutraAppState) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val selectedTab = appState.currentTab()
    val isSettingsDestination = appState.isSettingsDestination()

    Scaffold(
        contentWindowInsets = ScaffoldDefaults.contentWindowInsets.exclude(WindowInsets.statusBars)
            .exclude(WindowInsets.navigationBars),
        topBar = {
            DashboardTopBar(
                isSettingsDestination = isSettingsDestination,
                onSettingsToggle = appState::toggleSettings,
            )
        },
        bottomBar = {
            PulseSutraBottomBar(
                visible = !isSettingsDestination,
                selectedTab = selectedTab,
                onTabSelected = appState::selectTab,
            )
        },
    ) { padding ->
        BackgroundOrbs(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding),
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding),
        ) {
            AnimatedVisibility(
                visible = isOffline,
                modifier = Modifier.align(Alignment.CenterHorizontally),
            ) {
                OfflineBadge(modifier = Modifier.padding(4.dp))
            }
            PulseSutraNavGraph(
                modifier = Modifier
                    .padding(horizontal = 16.dp) // todo temporary padding at root
                    .fillMaxWidth()
                    .weight(1f),
                navController = appState.navController,
            )
        }
    }
}
