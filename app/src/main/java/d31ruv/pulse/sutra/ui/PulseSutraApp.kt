package d31ruv.pulse.sutra.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.NavigationRailItemDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.rememberNavigationSuiteScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import d31ruv.pulse.sutra.components.BackgroundOrbs
import d31ruv.pulse.sutra.components.OfflineBadge
import d31ruv.pulse.sutra.components.PulseSutraTab
import d31ruv.pulse.sutra.components.pulseSutraNavigationItem
import d31ruv.pulse.sutra.navigation.PulseSutraNavGraph
import d31ruv.pulse.sutra.util.rememberNavigationSuiteType

@Composable
fun PulseSutraApp(appState: PulseSutraAppState) {
    val colorScheme = MaterialTheme.colorScheme
    val selectedTab = appState.currentTab()
    val isSettingsDestination = appState.isSettingsDestination()
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val scaffoldState = rememberNavigationSuiteScaffoldState()
    val navigationItemColor = NavigationSuiteDefaults.itemColors(
        navigationRailItemColors = NavigationRailItemDefaults.colors(indicatorColor = Color.Transparent),
        navigationDrawerItemColors = NavigationDrawerItemDefaults.colors(),
        navigationBarItemColors = NavigationBarItemDefaults.colors(indicatorColor = Color.Transparent),
    )

    NavigationSuiteScaffold(
        state = scaffoldState,
        navigationSuiteColors = NavigationSuiteDefaults.colors(
            shortNavigationBarContainerColor = colorScheme.surface,
            navigationBarContainerColor = colorScheme.surface,
            navigationRailContainerColor = colorScheme.surface,
            navigationDrawerContainerColor = colorScheme.surface,
        ),
        navigationSuiteItems = {
            PulseSutraTab.entries.forEach { tab ->
                pulseSutraNavigationItem(
                    selected = tab == selectedTab,
                    onClick = { appState.selectTab(tab) },
                    tab = tab,
                    navigationItemColor = navigationItemColor,
                )
            }
        },
        layoutType = rememberNavigationSuiteType(),
    ) {
        BackgroundOrbs(Modifier.fillMaxSize())
        Column(modifier = Modifier.fillMaxSize()) {
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
                appState = appState,
            )
        }
    }

    LaunchedEffect(isSettingsDestination) {
        if (isSettingsDestination) scaffoldState.hide() else scaffoldState.show()
    }
}
