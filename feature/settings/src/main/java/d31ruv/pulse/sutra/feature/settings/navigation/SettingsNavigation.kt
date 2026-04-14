package d31ruv.pulse.sutra.feature.settings.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import d31ruv.pulse.sutra.feature.settings.SettingsScreen
import kotlinx.serialization.Serializable

/** Type-safe route object for the settings destination. */
@Serializable
object SettingsRoute

/** Registers the settings screen in the given [NavGraphBuilder]. */
fun NavGraphBuilder.settingsScreen() {
    composable<SettingsRoute> {
        SettingsScreen()
    }
}
