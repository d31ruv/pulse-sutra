package d31ruv.diecast.feature.profile.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.diecast.feature.profile.ProfileRoute
import kotlinx.serialization.Serializable

@Serializable
data object ProfileDestination

fun NavController.navigateToProfile() {
    navigate(ProfileDestination)
}

fun NavGraphBuilder.profileScreen(
    isOffline: Boolean,
    modifier: Modifier = Modifier,
) {
    composable<ProfileDestination> {
        ProfileRoute(
            isOffline = isOffline,
            modifier = modifier,
        )
    }
}

// TODO: temporary creating here
@Serializable
data object FavoritesRoute

@Serializable
data object ProfileRoute

