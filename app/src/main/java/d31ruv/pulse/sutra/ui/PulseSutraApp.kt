package d31ruv.pulse.sutra.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController

@Composable
fun PulseSutraApp(appState: PulseSutraAppState) {
    val navController = rememberNavController()
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            AppDestinations.entries.forEach {
                item(
                    icon = { Icon(it.icon, contentDescription = it.label) },
                    label = { Text(it.label) },
                    selected = currentDestination.isTopLevelDestinationInHierarchy(it.route),
                    onClick = {
                        navController.navigate(it.route) {
                            launchSingleTop = true
                            restoreState = true
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                        }
                    },
                )
            }
        },
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            snackbarHost = { SnackbarHost(snackbarHostState) },
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = AppDestinations.Home.route,
                modifier = Modifier.padding(innerPadding),
            ) {
                composable(AppDestinations.Home.route) {
                    TemplatePlaceholder(
                        title = if (isOffline) "Home (Offline)" else "Home",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                composable(AppDestinations.Favorites.route) {
                    TemplatePlaceholder(
                        title = "Favorites",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
            }
        }
    }
}

sealed class AppDestinations(
    val label: String,
    val icon: ImageVector,
    val route: String,
) {
    data object Home : AppDestinations(
        label = "Home",
        icon = Icons.Default.Home,
        route = "home",
    )

    data object Favorites : AppDestinations(
        label = "Favorites",
        icon = Icons.Default.Favorite,
        route = "favorites",
    )

    companion object {
        val entries = listOf(Home, Favorites)
    }
}

@Composable
fun TemplatePlaceholder(
    title: String,
    modifier: Modifier = Modifier,
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
    ) { innerPadding ->
        AssistChip(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxWidth(),
            onClick = {},
            label = { Text("$title is still a placeholder in the starter template") },
        )
    }
}

private fun NavDestination?.isTopLevelDestinationInHierarchy(
    route: String,
): Boolean {
    val destination = this ?: return false
    return destination.hierarchy.any { it.route == route }
}
