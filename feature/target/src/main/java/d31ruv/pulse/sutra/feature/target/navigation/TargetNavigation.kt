package d31ruv.pulse.sutra.feature.target.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.target.TargetDashboardScreen
import kotlinx.serialization.Serializable

/** Type-safe route object for the Target dashboard destination. */
@Serializable
object TargetRoute

/** Registers the Target dashboard screen in the given [NavGraphBuilder]. */
fun NavGraphBuilder.targetScreen(onSettingsClick: () -> Unit) {
    composable<TargetRoute> {
        TargetDashboardScreen(onSettingsClick = onSettingsClick)
    }
}
