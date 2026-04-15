package d31ruv.pulse.sutra.feature.settings.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeOut
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable

/** Type-safe route object for the settings destination. */
@Serializable
object SettingsRoute

/** Registers the settings screen in the given [NavGraphBuilder]. */
fun NavGraphBuilder.settingsScreen() {
    composable<SettingsRoute>(
        enterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        },
        exitTransition = {
            slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Left)
        },
        popEnterTransition = {
            slideIntoContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        },
        popExitTransition = {
            fadeOut() + slideOutOfContainer(towards = AnimatedContentTransitionScope.SlideDirection.Right)
        },
    ) {
        SettingsScreen()
    }
}
