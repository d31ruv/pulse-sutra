package d31ruv.diecast.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
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
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import d31ruv.diecast.feature.profile.navigation.FavoritesRoute
import d31ruv.diecast.feature.profile.navigation.ProfileDestination
import d31ruv.diecast.feature.profile.navigation.ProfileRoute
import d31ruv.diecast.feature.profile.navigation.profileScreen

@Composable
fun DiecastApp(appState: DieAppState) {
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
                startDestination = ProfileDestination,
                modifier = Modifier.padding(innerPadding),
            ) {
                profileScreen(
                    isOffline = isOffline,
                    modifier = Modifier.fillMaxSize(),
                )
                composable<FavoritesRoute> {
                    TemplatePlaceholder(
                        title = "Favorites",
                        modifier = Modifier.fillMaxSize(),
                    )
                }
                composable<ProfileRoute> {
                    TemplatePlaceholder(
                        title = "Profile",
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
    val route: Any,
) {
    data object Home : AppDestinations(
        label = "Home",
        icon = Icons.Default.Home,
        route = ProfileDestination,
    )

    data object Favorites : AppDestinations(
        label = "Favorites",
        icon = Icons.Default.Favorite,
        route = FavoritesRoute,
    )

    data object Profile : AppDestinations(
        label = "Profile",
        icon = Icons.Default.AccountBox,
        route = ProfileRoute,
    )

    companion object {
        val entries = listOf(Home, Favorites, Profile)
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
    route: Any,
): Boolean {
    val destination = this ?: return false
    return destination.hasRoute(route::class)
}
